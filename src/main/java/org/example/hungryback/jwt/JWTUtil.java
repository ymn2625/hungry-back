package org.example.hungryback.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.user.repository.TokenRepositoryImplement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTUtil {
    @Value("${spring.jwt.secret}")
    private String secretKey;
    private final TokenRepositoryImplement tokenRepositoryImplement;

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createRefreshToken(String email, String role) {
        long refreshPeriod = 1000L * 60L * 60L * 24L * 14;

        String refreshToken = Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        tokenRepositoryImplement.saveRefreshToken(refreshToken, email);

        return refreshToken;
    }

    public String createAccessToken(String email, String role) {
        long tokenPeriod = 1000L * 60L * 30L;

        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
