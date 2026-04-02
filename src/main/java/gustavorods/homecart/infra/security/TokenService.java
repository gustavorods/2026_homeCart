package gustavorods.homecart.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gustavorods.homecart.model.UsersModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.AlgorithmConstraints;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsersModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("homeCart")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(generateExpirationDate())
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating JWT Token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("homeCart")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception) {
            return null;
        }
    }

    public String generateRefreshToken(UsersModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("homeCart")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(generateRefreshExpirationDate())
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating refresh JWT Token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return Instant.now().plusSeconds(20);
    }

    private Instant generateRefreshExpirationDate() {
        return Instant.now().plusSeconds(120);
    }
}

