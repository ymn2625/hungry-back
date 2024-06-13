package org.example.hungryback.repository;

import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.entity.primaryKey.PartyMemberPk;
import org.example.hungryback.repository.resultSet.GetPartyMemberResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyMemberRepositoryJin extends JpaRepository<PartyMemberEntity, PartyMemberPk> {

    //진영만듦
    @Query(
            value = "SELECT u.user_email userEmail, u.user_name userName, u.user_nickname userNickname, u.user_profile_img userProfileImg, u.user_attractive userAttractive, pm.member_role memberRole FROM user u join party_member pm using(user_email) where pm.party_id = :partyId",
            nativeQuery = true
    )
    List<GetPartyMemberResultSet> findPartyMemberByPartyId(@Param("partyId") Integer partyId);




    boolean existsByUserEmailAndPartyId(String userEmail, Integer partyId);
}
