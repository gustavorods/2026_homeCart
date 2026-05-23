package gustavorods.homecart.controllers;

import gustavorods.homecart.dto.ResidenceRequestDTO;
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

    // Create residence route
    @PostMapping("/create")
    public ResponseEntity<?> createResidence(@RequestBody ResidenceRequestDTO body) {
        int code = residenceService.create(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("code", code)
        );
    }
}
