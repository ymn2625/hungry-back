package org.example.hungryback.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@RedisHash(value = "RefreshToken", timeToLive = 60)
public class RefreshTokenEntity {
    @Id
    private String userEmail;
    private String refreshToken;

    @Builder
    public RefreshTokenEntity(String refreshToken, String userEmail) {
        this.refreshToken = refreshToken;
        this.userEmail = userEmail;
    }
}
