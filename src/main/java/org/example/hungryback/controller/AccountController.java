package org.example.hungryback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.account.PatchNicknameRequestDto;
import org.example.hungryback.dto.request.account.PatchPasswordRequestDto;
import org.example.hungryback.dto.request.account.PatchProfileImgRequestDto;
import org.example.hungryback.dto.response.account.GetUserResponseDto;
import org.example.hungryback.dto.response.account.PatchNicknameResponseDto;
import org.example.hungryback.dto.response.account.PatchPasswordResponseDto;
import org.example.hungryback.dto.response.account.PatchProfileImgResponseDto;
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

    @GetMapping("/user")
    public ResponseEntity<? super GetUserResponseDto> getUser(@AuthenticationPrincipal String userEmail) {
        ResponseEntity<? super GetUserResponseDto> response = accountService.getUser(userEmail);
        return response;
    }

}
