package org.example.hungryback.dto.request.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageRoomDto {
    private String roomId;

    @Builder
    public MessageRoomDto(String roomId) {
        this.roomId = roomId;
    }
}
