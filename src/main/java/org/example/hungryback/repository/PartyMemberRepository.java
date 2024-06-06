package org.example.hungryback.repository;

import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.entity.primaryKey.PartyMemberPk;
import org.example.hungryback.repository.resultSet.GetPartyMemberListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMemberEntity, PartyMemberPk> {
    PartyMemberEntity findByUserEmailAndPartyId(String userEmail, Integer partyId);
    PartyMemberEntity findByPartyIdAndMemberRole(Integer partyId, Integer memberRole);
    @Query(
            value = "SELECT u.user_email userEmail, u.user_nickname userNickname, u.user_profile_img userProfileImg, pm.member_role memberRole FROM user u join party_member pm using(user_email) where pm.party_id = :partyId order by member_role desc",
            nativeQuery = true
    )
    List<GetPartyMemberListResultSet> findPartyMembersByPartyId(@Param("partyId") Integer partyId);
}
