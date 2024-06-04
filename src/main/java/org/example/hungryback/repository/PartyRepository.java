package org.example.hungryback.repository;

import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Integer> {

    List<PartyEntity> findByPartyStoreId(Integer storeId);

    PartyEntity findByPartyId(Integer partyId);

}
