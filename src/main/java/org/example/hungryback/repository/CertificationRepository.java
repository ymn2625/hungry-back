package org.example.hungryback.repository;

import org.example.hungryback.entity.CertificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends CrudRepository<CertificationEntity, String> {
    CertificationEntity findByUserTel(String userTel);
    void deleteByUserTel(String userTel);
}
