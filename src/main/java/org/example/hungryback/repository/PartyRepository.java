package org.example.hungryback.repository;

import org.example.hungryback.entity.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Integer> {
    @Query(
            value = "SELECT * FROM party WHERE party_id IN (SELECT party_id FROM party_member WHERE user_email = :userEmail)",
            nativeQuery = true
    )
    List<PartyEntity> findByUserEmail(@Param("userEmail") String userEmail);

    List<PartyEntity> findByStoreId(Integer storeId);

    PartyEntity findByPartyId(Integer partyId);

    boolean existsByPartyId(Integer partyId);

}
