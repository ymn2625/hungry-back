package org.example.hungryback.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt-secret-key}")
    private String secretKey;
    @Value("${jwt-access-expiration-time}")
    private int tokenExpiredMs;
    @Value("${jwt-refresh-expiration-time}")
    private int refreshTokenExpiredMs;

    // 키 생성
    private Key generateSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 엑세스 토큰 생성
    public String createToken(String userEmail) {
        Key key = generateSecretKey();

        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiredMs))
                .compact();

        return jwt;
    }

    // 리프레쉬 토큰 생성
    public String createRefreshToken(String userEmail) {
        Key key = generateSecretKey();

        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiredMs))
                .compact();

        return jwt;
    }

    public void refreshTokenCookie(HttpServletResponse response, String refreshToken) {
        response.addCookie(createCookie("refreshToken", refreshToken));
    }

    public Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(refreshTokenExpiredMs/1000);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }

    // 토큰 유효 여부 확인 (true: 유효 / false: 무효)
    public Boolean isValid(String jwt) {
        Key key = generateSecretKey();

        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody().getExpiration().after(new Date());
        } catch (Exception exception) {
            return false;
        }
    }

    // userEmail 얻기
    public String getUserEmail(String jwt) {
        Key key = generateSecretKey();

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody().getSubject();
    }
}