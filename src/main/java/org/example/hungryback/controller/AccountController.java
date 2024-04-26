package org.example.hungryback.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.certification.CheckCertificationRequestDto;
import org.example.hungryback.dto.request.certification.SendCertificationRequestDto;
import org.example.hungryback.dto.response.certification.CheckCertificationResponseDto;
import org.example.hungryback.dto.response.certification.SendCertificationResponseDto;
import org.example.hungryback.dto.request.user.*;
import org.example.hungryback.dto.response.user.*;
import org.example.hungryback.service.CertificationService;
import org.example.hungryback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final CertificationService certificationService;

    @PostMapping("/sign-up-app")
    public ResponseEntity<? super SignUpResponseDto> signUpApp(@RequestBody @Valid SignUpAppRequestDto requestBody) {
        ResponseEntity<? super SignUpResponseDto> response = userService.signUpApp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestBody, HttpServletResponse servletResponse) {
        ResponseEntity<? super SignInResponseDto> response = userService.signIn(requestBody, servletResponse);
        return response;
    }

    @PostMapping("/sms-certification")
    public ResponseEntity<? super SendCertificationResponseDto> sendCertification(@RequestBody @Valid SendCertificationRequestDto requestBody) {
        ResponseEntity<? super SendCertificationResponseDto> response = certificationService.sendCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> signIn(@RequestBody @Valid CheckCertificationRequestDto requestBody) {
        ResponseEntity<? super CheckCertificationResponseDto> response = certificationService.checkCertification(requestBody);
        return response;
    }

    @PatchMapping("/user/password")
    public ResponseEntity<? super PatchPasswordResponseDto> patchPassword(@RequestBody @Valid PatchPasswordRequestDto requestBody) {
        ResponseEntity<? super PatchPasswordResponseDto> response = userService.patchPassword(requestBody);
        return response;
    }

    @PatchMapping("/user/profile-img")
    public ResponseEntity<? super PatchProfileImgResponseDto> patchProfileImg(@RequestBody @Valid PatchProfileImgRequestDto requestBody) {
        ResponseEntity<? super PatchProfileImgResponseDto> response = userService.patchProfileImg(requestBody);
        return response;
    }

    @PatchMapping("/user/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(@RequestBody @Valid PatchNicknameRequestDto requestBody) {
        ResponseEntity<? super PatchNicknameResponseDto> response = userService.patchNickname(requestBody);
        return response;
    }

    @GetMapping("/user")
    public ResponseEntity<? super GetUserResponseDto> getUser(@AuthenticationPrincipal String userEmail) {
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(userEmail);
        return response;
    }

}
