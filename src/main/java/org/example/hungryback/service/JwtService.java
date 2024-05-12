package org.example.hungryback.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.hungryback.dto.response.jwt.JwtResponseDto;
import org.springframework.http.ResponseEntity;

public interface JwtService {
    ResponseEntity<? super JwtResponseDto> jwtReissue (String refreshToken, HttpServletResponse response);
}
