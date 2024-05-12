package org.example.hungryback.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.response.jwt.JwtResponseDto;
import org.example.hungryback.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jwt")
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    @GetMapping("/reissue")
    public ResponseEntity<? super JwtResponseDto> reissueToken (@CookieValue("refreshToken") String refreshToken, HttpServletResponse servleteResponse) {
        ResponseEntity<? super JwtResponseDto> response = jwtService.jwtReissue(refreshToken, servleteResponse);
        return response;
    }
}
