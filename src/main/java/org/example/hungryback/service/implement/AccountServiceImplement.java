package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.account.PatchNicknameRequestDto;
import org.example.hungryback.dto.request.account.PatchPasswordRequestDto;
import org.example.hungryback.dto.request.account.PatchProfileImgRequestDto;
import org.example.hungryback.dto.request.account.PatchTelRequestDto;
import org.example.hungryback.dto.response.account.*;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImplement implements AccountService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto) {
        try {

            String userEmail = dto.getUserEmail();
            String userNickname = dto.getUserNickname();

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return PatchNicknameResponseDto.notExistUser();

            userEntity.patchNickname(userNickname);

            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImgResponseDto> patchProfileImg(PatchProfileImgRequestDto dto) {

        try {

            String userEmail = dto.getUserEmail();
            String userProfileImg = dto.getUserProfileImg();

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return PatchProfileImgResponseDto.notExistUser();

            userEntity.patchProfileImg(userProfileImg);

            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchProfileImgResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchPasswordResponseDto> patchPassword(PatchPasswordRequestDto dto) {

        try {

            String userEmail = dto.getUserEmail();
            String userPassword = dto.getUserPassword();
            String encodedPassword = passwordEncoder.encode(userPassword);

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return PatchProfileImgResponseDto.notExistUser();

            userEntity.patchPassword(encodedPassword);

            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchPasswordResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchTelResponseDto> patchTel(PatchTelRequestDto dto) {
        try {

            String userEmail = dto.getUserEmail();
            String userTel = dto.getUserTel();

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return PatchTelResponseDto.notExistUser();

            userEntity.patchTel(userTel);

            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchTelResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String userEmail) {

        UserEntity userEntity;

        try {

            userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return GetUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return GetUserResponseDto.databaseError();
        }
        return GetUserResponseDto.success(userEntity);
    }
}
