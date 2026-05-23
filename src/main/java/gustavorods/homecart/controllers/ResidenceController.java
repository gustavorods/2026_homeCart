package gustavorods.homecart.controllers;

import gustavorods.homecart.dto.AddMemberRequestDTO;
import gustavorods.homecart.dto.ResidenceRequestDTO;
import gustavorods.homecart.service.AddResidenceMemberService;
import gustavorods.homecart.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/residence")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;
    @Autowired
    private AddResidenceMemberService addResidenceMemberService;

    // Create residence route
    @PostMapping("/create")
    public ResponseEntity<?> createResidence(@RequestBody ResidenceRequestDTO body) {
        int code = residenceService.create(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("code", code)
        );
    }

    @PostMapping("/add-member")
    public ResponseEntity<?> addMember(@RequestBody AddMemberRequestDTO body) {
        boolean status = addResidenceMemberService.addResidenceMember(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("menssage: ", "member:" + body.email() + " added successfully")
        );
    }
}
