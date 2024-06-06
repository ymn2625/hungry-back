package org.example.hungryback.dto.request.party;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostPartyRequestDto {
    private int partyLimit;
    private String partyName;
    private String partyDescription;
    private String partyTime;
    private String partyStartTime;
    private String partyEndTime;
    private int storeId;
    private String userEmail;
}
