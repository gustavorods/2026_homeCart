package gustavorods.homecart.controllers;

import gustavorods.homecart.dto.RegisterRequestDTO;
import gustavorods.homecart.infra.exceptions.UserAlreadyExistsException;
import gustavorods.homecart.model.UsersModel;
import gustavorods.homecart.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register route
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO body) {
        Optional<UsersModel> user = this.userRepository.findByEmail(body.email());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        UsersModel newUser = new UsersModel();
        newUser.setEmail(body.email());

        String password = passwordEncoder.encode(body.password());
        newUser.setPassword(password);

        newUser.setName(body.name());

        this.userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("email", body.email())
        );
    }

}
