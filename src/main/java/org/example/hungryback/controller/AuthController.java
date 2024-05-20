package org.example.hungryback.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.account.PatchPasswordRequestDto;
import org.example.hungryback.dto.request.auth.*;
import org.example.hungryback.dto.response.account.PatchPasswordResponseDto;
import org.example.hungryback.dto.response.auth.*;
import org.example.hungryback.service.AccountService;
import org.example.hungryback.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AccountService accountService;

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

    @PostMapping("/find-account")
    public ResponseEntity<? super FindAccountResponseDto> findAccount(@RequestBody @Valid FindAccountRequestDto requestBody) {
        ResponseEntity<? super FindAccountResponseDto> response = authService.findAccount(requestBody);
        return response;
    }

    @PatchMapping("/password")
    public ResponseEntity<? super PatchPasswordResponseDto> patchPassword(@RequestBody @Valid PatchPasswordRequestDto requestBody) {
        ResponseEntity<? super PatchPasswordResponseDto> response = accountService.patchPassword(requestBody);
        return response;
    }

}
