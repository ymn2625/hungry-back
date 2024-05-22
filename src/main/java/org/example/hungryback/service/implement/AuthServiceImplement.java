package org.example.hungryback.service.implement;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.auth.*;
import org.example.hungryback.dto.response.auth.*;
import org.example.hungryback.entity.CertificationEntity;
import org.example.hungryback.entity.RefreshTokenEntity;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.provider.CertificationProvider;
import org.example.hungryback.provider.JwtProvider;
import org.example.hungryback.repository.CertificationRepository;
import org.example.hungryback.repository.RefreshTokenRepository;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    @Value("${jwt-access-expiration-time}")
    private int tokenExpiredMs;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CertificationRepository certificationRepository;

    private final JwtProvider jwtProvider;
    private final CertificationProvider certificationProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
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

        return SignInResponseDto.success(token, tokenExpiredMs);
    }

    // 이메일 중복 체크
    @Override
    public ResponseEntity<? super CheckEmailResponseDto> checkEmail(CheckEmailRequestDto dto) {
        try {

            String userEmail = dto.getUserEmail();
            boolean isExistEmail = userRepository.existsByUserEmail(userEmail);
            if(isExistEmail) return CheckEmailResponseDto.duplicateEmail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CheckEmailResponseDto.success();
    }

    // 인증번호 전송
    @Override
    public ResponseEntity<? super SendCertificationResponseDto> sendCertification(SendCertificationRequestDto dto) {
        try {

            String userTel = dto.getUserTel();
            String certificationNumber = certificationProvider.createRandomNumber();

            boolean isSuccessed = certificationProvider.sendCertification(userTel, certificationNumber);
            if(!isSuccessed) return SendCertificationResponseDto.messageSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userTel, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SendCertificationResponseDto.success();
    }

    // 인증번호 확인
    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        String userEmail = null;

        try {

            String userName = dto.getUserName();
            String userTel = dto.getUserTel();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserTel(userTel);
            if(certificationEntity == null || !certificationEntity.getCertificationNumber().equals(certificationNumber))
                return CheckCertificationResponseDto.certificationFail();

            certificationRepository.delete(certificationEntity);

            UserEntity userEntity = userRepository.findByUserTel(userTel);
            // 이름까지 확인하도록 수정
            if(userEntity != null && userEntity.getUserName().equals(userName)) userEmail = userEntity.getUserEmail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CheckCertificationResponseDto.success(userEmail);
    }

    @Override
    public ResponseEntity<? super FindAccountResponseDto> findAccount(FindAccountRequestDto dto) {
        UserEntity userEntity = null;
        try {
            String userEmail = dto.getUserEmail();
            userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return FindAccountResponseDto.notExistUser();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return FindAccountResponseDto.success(userEntity);
    }
}
