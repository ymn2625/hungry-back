package org.example.hungryback.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.auth.*;
import org.example.hungryback.dto.response.auth.*;
import org.example.hungryback.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUpApp(@RequestBody @Valid SignUpRequestDto requestBody) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestBody, HttpServletResponse servletResponse) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody, servletResponse);
        return response;
    }

    @PostMapping("/check-email")
    public ResponseEntity<? super CheckEmailResponseDto> checkEmail(@RequestBody @Valid CheckEmailRequestDto requestBody) {
        ResponseEntity<? super CheckEmailResponseDto> response = authService.checkEmail(requestBody);
        return response;
    }

    @PostMapping("/sms-certification")
    public ResponseEntity<? super SendCertificationResponseDto> sendCertification(@RequestBody @Valid SendCertificationRequestDto requestBody) {
        ResponseEntity<? super SendCertificationResponseDto> response = authService.sendCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> signIn(@RequestBody @Valid CheckCertificationRequestDto requestBody) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

}
