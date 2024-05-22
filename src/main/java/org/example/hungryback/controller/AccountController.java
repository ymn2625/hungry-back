package org.example.hungryback.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.account.PatchNicknameRequestDto;
import org.example.hungryback.dto.request.account.PatchPasswordRequestDto;
import org.example.hungryback.dto.request.account.PatchProfileImgRequestDto;
import org.example.hungryback.dto.request.account.PatchTelRequestDto;
import org.example.hungryback.dto.request.account.ResignationRequestDto;
import org.example.hungryback.dto.request.account.SignOutRequestDto;
import org.example.hungryback.dto.response.account.*;
import org.example.hungryback.dto.response.account.ResignationResponseDto;
import org.example.hungryback.dto.response.account.SignOutResponseDto;
import org.example.hungryback.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PatchMapping("/password")
    public ResponseEntity<? super PatchPasswordResponseDto> patchPassword(@RequestBody @Valid PatchPasswordRequestDto requestBody) {
        ResponseEntity<? super PatchPasswordResponseDto> response = accountService.patchPassword(requestBody);
        return response;
    }

    @PatchMapping("/profile-img")
    public ResponseEntity<? super PatchProfileImgResponseDto> patchProfileImg(@RequestBody @Valid PatchProfileImgRequestDto requestBody) {
        ResponseEntity<? super PatchProfileImgResponseDto> response = accountService.patchProfileImg(requestBody);
        return response;
    }

    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(@RequestBody @Valid PatchNicknameRequestDto requestBody) {
        ResponseEntity<? super PatchNicknameResponseDto> response = accountService.patchNickname(requestBody);
        return response;
    }

    @PatchMapping("/tel")
    public ResponseEntity<? super PatchTelResponseDto> patchNickname(@RequestBody @Valid PatchTelRequestDto requestBody) {
        ResponseEntity<? super PatchTelResponseDto> response = accountService.patchTel(requestBody);
        return response;
    }

    @GetMapping("/user")
    public ResponseEntity<? super GetUserResponseDto> getUser(@AuthenticationPrincipal String userEmail) {
        ResponseEntity<? super GetUserResponseDto> response = accountService.getUser(userEmail);
        return response;
    }

    @PostMapping("/resignation")
    public ResponseEntity<? super ResignationResponseDto> resignation(@RequestBody @Valid ResignationRequestDto requestBody, HttpServletResponse servletResponse) {
        ResponseEntity<? super ResignationResponseDto> response = accountService.resignation(requestBody, servletResponse);
        return response;
    }

    @PostMapping("/sign-out")
    public ResponseEntity<? super SignOutResponseDto> signOut(@RequestBody @Valid SignOutRequestDto requestBody, HttpServletResponse servletResponse) {
        ResponseEntity<? super SignOutResponseDto> response = accountService.signOut(requestBody, servletResponse);
        return response;
    }

}
