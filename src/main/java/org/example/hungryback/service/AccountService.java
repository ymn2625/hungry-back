package org.example.hungryback.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.hungryback.dto.request.account.*;
import org.example.hungryback.dto.response.account.*;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname (PatchNicknameRequestDto dto);
    ResponseEntity<? super PatchProfileImgResponseDto> patchProfileImg (PatchProfileImgRequestDto dto);
    ResponseEntity<? super PatchPasswordResponseDto> patchPassword (PatchPasswordRequestDto dto);
    ResponseEntity<? super PatchTelResponseDto> patchTel (PatchTelRequestDto dto);
    ResponseEntity<? super GetUserResponseDto> getUser (String userEmail);
    ResponseEntity<? super ResignationResponseDto> resignation (ResignationRequestDto dto, HttpServletResponse response);
    ResponseEntity<? super SignOutResponseDto> signOut (SignOutRequestDto dto, HttpServletResponse response);
}
