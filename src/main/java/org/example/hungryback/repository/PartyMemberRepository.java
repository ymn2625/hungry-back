package org.example.hungryback.repository;

import org.example.hungryback.entity.party.PartyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyMemberRepository extends JpaRepository<PartyMemberEntity, Long> {
}
