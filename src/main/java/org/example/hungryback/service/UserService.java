package org.example.hungryback.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.hungryback.dto.request.user.*;
import org.example.hungryback.dto.response.user.*;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super SignUpResponseDto> signUpApp (SignUpAppRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUpSocial (SignUpSocialRequestDto dto, String userEmail);
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto, HttpServletResponse response);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname (PatchNicknameRequestDto dto);
    ResponseEntity<? super PatchProfileImgResponseDto> patchProfileImg (PatchProfileImgRequestDto dto);
    ResponseEntity<? super PatchPasswordResponseDto> patchPassword (PatchPasswordRequestDto dto);
    ResponseEntity<? super GetUserResponseDto> getUser (String userEmail);
}
