package org.example.hungryback.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.dto.request.message.SendMessageRequestDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="message")
@Table(name="message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private String userEmail;
    private int partyId;
    private String content;
    private String sendTime;

    public MessageEntity(SendMessageRequestDto dto) {
        this.userEmail = dto.getUserEmail();
        this.partyId = dto.getPartyId();
        this.content = dto.getContent();
        this.sendTime = dto.getSendTime();
    }
}
