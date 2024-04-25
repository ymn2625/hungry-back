package org.example.hungryback.repository;

import org.example.hungryback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserTel(String userTel);
    UserEntity findByUserEmail(String userEmail);
    UserEntity findByUserTel(String userTel);
}
