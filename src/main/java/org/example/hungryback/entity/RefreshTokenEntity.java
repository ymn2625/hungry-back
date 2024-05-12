package org.example.hungryback.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@RedisHash(value = "RefreshToken", timeToLive = 60 * 60 * 24 * 14)
public class RefreshTokenEntity {
    @Id
    private String userEmail;
    @Indexed
    private String refreshToken;

    @Builder
    public RefreshTokenEntity(String refreshToken, String userEmail) {
        this.refreshToken = refreshToken;
        this.userEmail = userEmail;
    }

    public void patchEntity(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
