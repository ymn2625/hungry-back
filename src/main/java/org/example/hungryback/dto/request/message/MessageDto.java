package org.example.hungryback.dto.request.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageDto {
    private String userEmail;
    private Integer partyId;
    private String content;

    public MessageDto(String userEmail, Integer partyId, String content) {
        this.userEmail = userEmail;
        this.partyId = partyId;
        this.content = content;
    }
}
