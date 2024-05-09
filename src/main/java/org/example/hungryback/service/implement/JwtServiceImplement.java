package org.example.hungryback.service.implement;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.response.jwt.JwtResponseDto;
import org.example.hungryback.entity.RefreshTokenEntity;
import org.example.hungryback.provider.JwtProvider;
import org.example.hungryback.repository.RefreshTokenRepository;
import org.example.hungryback.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImplement implements JwtService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<? super JwtResponseDto> jwtReissue(String refreshToken, HttpServletResponse response) {
        String token = null;

        try {

            RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken);    // 리프레시 토큰이 저장 되어있지 않을 때
            if(refreshTokenEntity == null) {
                return JwtResponseDto.reissueFail();
            }

            String userEmail = refreshTokenEntity.getUserEmail();

            token = jwtProvider.createToken(userEmail); // 토큰 재발급
            refreshToken = jwtProvider.createRefreshToken(userEmail);   // 리프레시 토큰 재발급

            refreshTokenEntity.patchEntity(refreshToken);
            refreshTokenRepository.save(refreshTokenEntity);    // 리프레시 토큰 수정후 저장

            jwtProvider.refreshTokenCookie(response, refreshToken); // 리프레시 토큰 헤더에 담기

        } catch (Exception exception) {
            return ResponseDto.databaseError();
        }

        return JwtResponseDto.success(token);
    }
}
