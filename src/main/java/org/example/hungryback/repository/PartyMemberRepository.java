package org.example.hungryback.repository;

import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.entity.primaryKey.PartyMemberPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMemberEntity, PartyMemberPk> {
    PartyMemberEntity findByUserEmailAndPartyId(String userEmail, Integer partyId);

}
