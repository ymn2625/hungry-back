package org.example.hungryback.repository;

import org.example.hungryback.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
    RefreshTokenEntity findByUserEmail(String userEmail);
    RefreshTokenEntity findByRefreshToken(String refreshToken);
}
