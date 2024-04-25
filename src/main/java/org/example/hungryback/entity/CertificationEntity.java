package org.example.hungryback.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "certification", timeToLive = 60 * 5)
public class CertificationEntity {
    @Id
    private String userTel;
    private String certificationNumber;

    public CertificationEntity(String userTel, String certificationNumber) {
        this.userTel = userTel;
        this.certificationNumber = certificationNumber;
    }
}
