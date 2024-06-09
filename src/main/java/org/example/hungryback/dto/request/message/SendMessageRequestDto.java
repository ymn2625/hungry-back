package org.example.hungryback.dto.request.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequestDto {
    private String userEmail;
    private Integer partyId;
    private String content;
    private String sendTime;
}
