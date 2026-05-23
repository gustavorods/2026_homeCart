package gustavorods.homecart.controllers;

import gustavorods.homecart.dto.LoginRequestDTO;
import gustavorods.homecart.dto.RegisterRequestDTO;
import gustavorods.homecart.infra.exceptions.InvalidCredentialsException;
import gustavorods.homecart.infra.exceptions.UserAlreadyExistsException;
import gustavorods.homecart.infra.exceptions.UserNotFoundException;
import gustavorods.homecart.infra.security.TokenService;
import gustavorods.homecart.model.UsersModel;
import gustavorods.homecart.repository.UsersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    // Register route
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO body) {
        Optional<UsersModel> user = this.usersRepository.findByEmail(body.email());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        UsersModel newUser = new UsersModel();
        newUser.setEmail(body.email());

        String password = passwordEncoder.encode(body.password());
        newUser.setPassword(password);

        newUser.setName(body.name());

        this.usersRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("email", body.email())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO body, HttpServletResponse response) throws AuthenticationException {

        UsersModel user = this.usersRepository.findByEmail(body.email())
                .orElseThrow(() -> new UserNotFoundException(body.email()));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = tokenService.generateToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(120);

        response.addCookie(cookie);

        return ResponseEntity.ok(Map.of("accessToken", accessToken));
    }
}
