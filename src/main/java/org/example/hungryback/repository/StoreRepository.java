package org.example.hungryback.repository;

import org.example.hungryback.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {
    @Query("SELECT s FROM StoreEntity s WHERE lower(s.storeName) LIKE %:keyword1% OR lower(s.storeAddress) LIKE %:keyword2%")
    List<StoreEntity> findByStoreSearchList(@Param("keyword1") String keyword1, @Param("keyword2") String keyword2);

    boolean existsByStoreId(int storeId);
}
