package org.example.hungryback.repository;

import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.repository.resultSet.GetPartyByUserEmailResultSet;
import org.example.hungryback.repository.resultSet.GetPartyResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Integer> {
    @Query(
            value = "SELECT " +
                    "p.party_limit partyLimit, " +
                    "p.store_id storeId, " +
                    "p.party_count partyCount, " +
                    "p.party_id partyId, " +
                    "p.party_description partyDescription, " +
                    "p.party_end_time partyEndTime, " +
                    "p.party_img partyImg, " +
                    "p.party_name partyName, " +
                    "p.party_start_time partyStartTime, " +
                    "p.party_time partyTime, " +
                    "m.content " +
                    "FROM (SELECT party_id, MAX(send_time) AS max_send_time FROM message GROUP BY party_id) AS latest_messages " +
                    "JOIN message AS m ON latest_messages.party_id = m.party_id AND latest_messages.max_send_time = m.send_time " +
                    "JOIN party AS p ON m.party_id = p.party_id JOIN party_member AS pm ON pm.party_id = p.party_id " +
                    "WHERE pm.user_email = :userEmail " +
                    "ORDER BY m.send_time desc",
            nativeQuery = true
    )
    List<GetPartyByUserEmailResultSet> findByUserEmail(@Param("userEmail") String userEmail);

    List<PartyEntity> findByStoreId(Integer storeId);

    PartyEntity findByPartyId(Integer partyId);

    boolean existsByPartyId(Integer partyId);

    @Query(
            value = "SELECT " +
                    "p.party_limit partyLimit, " +
                    "p.store_id storeId, " +
                    "p.party_count partyCount, " +
                    "p.party_id partyId, " +
                    "p.party_description partyDescription, " +
                    "p.party_end_time partyEndTime, " +
                    "p.party_img partyImg, " +
                    "p.party_name partyName, " +
                    "p.party_start_time partyStartTime, " +
                    "p.party_time partyTime, " +
                    "s.store_name storeName " +
                    "FROM party p " +
                    "JOIN store s " +
                    "ON s.store_id = p.store_id " +
                    "WHERE p.party_id = :partyId ",
            nativeQuery = true
    )
    GetPartyResultSet findPartyInfoByPartyId(@Param("partyId") Integer partyId);

}
