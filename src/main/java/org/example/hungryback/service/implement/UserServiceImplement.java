package org.example.hungryback.service.implement;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.user.*;
import org.example.hungryback.dto.response.user.*;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.entity.RefreshTokenEntity;
import org.example.hungryback.provider.JwtProvider;
import org.example.hungryback.repository.RefreshTokenRepository;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.repository.CertificationRepository;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CertificationRepository certificationRepository;

    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 앱 회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUpApp(SignUpAppRequestDto dto) {
        try {

            String userEmail = dto.getUserEmail();
            String userTel = dto.getUserTel();

            boolean isExistEmail = userRepository.existsByUserEmail(userEmail);
            if(isExistEmail) return SignUpResponseDto.duplicateEmail();
            boolean isExistTel = userRepository.existsByUserTel(userTel);
            if(isExistTel) return SignUpResponseDto.duplicateTel();

            String userPassword = dto.getUserPassword();
            String encodedPassword = passwordEncoder.encode(userPassword);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    // 소셜 로그인 회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUpSocial(SignUpSocialRequestDto dto, String userEmail) {
        try {

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return SignUpResponseDto.notExistUser();

            userEntity.signUpUser(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    // 로그인
    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto, HttpServletResponse response) {

        String token = null;

        try {

            String userEmail = dto.getUserEmail();
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return SignInResponseDto.signInFail();

            String userPassword = dto.getUserPassword();
            String encodedPassword = userEntity.getUserPassword();
            boolean isMatched = passwordEncoder.matches(userPassword, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.createToken(userEmail);
            String refreshToken = jwtProvider.createRefreshToken(userEmail);

            RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(refreshToken, userEmail);
            refreshTokenRepository.save(refreshTokenEntity);

            jwtProvider.refreshTokenCookie(response, refreshToken);

        } catch (Exception e) {
            ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

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
