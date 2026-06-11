package com.task1.user_management_api.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
@Service
public class JwtService {

    private static final String SECRET =
            "mySecretKeymySecretKeymySecretKeymySecretKey";
    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 86400000
                        )
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
