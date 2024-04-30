package org.example.hungryback.repository;

import org.example.hungryback.entity.party.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<PartyEntity, Long> {
}
