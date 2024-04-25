package org.example.hungryback.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Repository
public class TokenRepositoryImplement {
    private final String PREFIX = "refresh token: ";
    private final int LIMIT_TIME = 60 * 60 * 24 * 14;;
    private final StringRedisTemplate stringRedisTemplate;

    public void saveRefreshToken(String refreshToken, String email) {
        stringRedisTemplate.opsForValue()
                .set(PREFIX + email, refreshToken, Duration.ofSeconds(LIMIT_TIME));
    }

    public String getRefreshToken(String email) {
        return stringRedisTemplate.opsForValue().get(PREFIX + email);
    }

    public void deleteRefreshToken(String email) {
        stringRedisTemplate.delete(PREFIX + email);
    }
}
