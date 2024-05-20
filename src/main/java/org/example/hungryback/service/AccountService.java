package org.example.hungryback.service;

import org.example.hungryback.dto.request.account.PatchNicknameRequestDto;
import org.example.hungryback.dto.request.account.PatchPasswordRequestDto;
import org.example.hungryback.dto.request.account.PatchProfileImgRequestDto;
import org.example.hungryback.dto.request.account.PatchTelRequestDto;
import org.example.hungryback.dto.response.account.*;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname (PatchNicknameRequestDto dto);
    ResponseEntity<? super PatchProfileImgResponseDto> patchProfileImg (PatchProfileImgRequestDto dto);
    ResponseEntity<? super PatchPasswordResponseDto> patchPassword (PatchPasswordRequestDto dto);
    ResponseEntity<? super PatchTelResponseDto> patchTel (PatchTelRequestDto dto);
    ResponseEntity<? super GetUserResponseDto> getUser (String userEmail);
}
