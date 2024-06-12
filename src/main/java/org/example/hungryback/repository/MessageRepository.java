package org.example.hungryback.repository;

import org.example.hungryback.entity.MessageEntity;
import org.example.hungryback.repository.resultSet.GetMessageResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    @Query(
            value = "SELECT m.message_id messageId, u.user_email userEmail, u.user_nickname userNickname, u.user_profile_img userProfileImg, m.party_id partyId, m.content, m.send_time sendTime FROM user u join message m using(user_email) where m.party_id = :partyId order by sendTime desc",
            nativeQuery = true
    )
    List<GetMessageResultSet> findMessagesByPartyId(@Param("partyId") Integer partyId);
}
