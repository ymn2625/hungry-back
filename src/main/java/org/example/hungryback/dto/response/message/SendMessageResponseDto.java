package org.example.hungryback.dto.response.message;

import lombok.Getter;
import org.example.hungryback.dto.request.message.SendMessageRequestDto;

@Getter
public class SendMessageResponseDto {
    private int messageId;
    private String userEmail;
    private int partyId;
    private String content;
    private String sendTime;

    public SendMessageResponseDto(SendMessageRequestDto requestDto, int messageId) {
        this.messageId = messageId;
        this.userEmail = requestDto.getUserEmail();
        this.partyId = requestDto.getPartyId();
        this.content = requestDto.getContent();
        this.sendTime = requestDto.getSendTime();
    }
}
