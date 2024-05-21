package org.example.hungryback.service.implement;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.account.*;
import org.example.hungryback.dto.response.account.*;
import org.example.hungryback.entity.RefreshTokenEntity;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.repository.RefreshTokenRepository;
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
    private final RefreshTokenRepository refreshTokenRepository;

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

    // 로그아웃
    @Override
    public ResponseEntity<? super SignOutResponseDto> signOut(SignOutRequestDto dto, HttpServletResponse response) {
        try {
            String userEmail = dto.getUserEmail();

            RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByUserEmail(userEmail);
            if(refreshTokenEntity != null) {
                refreshTokenRepository.delete(refreshTokenEntity);
            }

            Cookie cookie = new Cookie("refreshToken", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignOutResponseDto.success();
    }

    // 회원탈퇴
    @Override
    public ResponseEntity<? super ResignationResponseDto> resignation(ResignationRequestDto dto, HttpServletResponse response) {
        try {
            String userEmail = dto.getUserEmail();

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return ResignationResponseDto.notExistUser();

            userRepository.delete(userEntity);

            RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByUserEmail(userEmail);
            if(refreshTokenEntity != null) {
                refreshTokenRepository.delete(refreshTokenEntity);
            }

            Cookie cookie = new Cookie("refreshToken", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResignationResponseDto.success();
    }

}
