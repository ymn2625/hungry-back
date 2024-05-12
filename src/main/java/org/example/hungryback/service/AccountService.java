package org.example.hungryback.service;

import org.example.hungryback.dto.request.account.PatchNicknameRequestDto;
import org.example.hungryback.dto.request.account.PatchPasswordRequestDto;
import org.example.hungryback.dto.request.account.PatchProfileImgRequestDto;
import org.example.hungryback.dto.response.account.GetUserResponseDto;
import org.example.hungryback.dto.response.account.PatchNicknameResponseDto;
import org.example.hungryback.dto.response.account.PatchPasswordResponseDto;
import org.example.hungryback.dto.response.account.PatchProfileImgResponseDto;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname (PatchNicknameRequestDto dto);
    ResponseEntity<? super PatchProfileImgResponseDto> patchProfileImg (PatchProfileImgRequestDto dto);
    ResponseEntity<? super PatchPasswordResponseDto> patchPassword (PatchPasswordRequestDto dto);
    ResponseEntity<? super GetUserResponseDto> getUser (String userEmail);
}
