package org.example.hungryback.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.hungryback.dto.request.auth.*;
import org.example.hungryback.dto.response.auth.*;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<? super SignUpResponseDto> signUp (SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto, HttpServletResponse response);
    ResponseEntity<? super CheckEmailResponseDto> checkEmail (CheckEmailRequestDto dto);
    ResponseEntity<? super SendCertificationResponseDto> sendCertification(SendCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super FindAccountResponseDto> findAccount(FindAccountRequestDto dto);
}
