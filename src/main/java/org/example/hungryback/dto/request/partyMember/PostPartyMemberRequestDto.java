package org.example.hungryback.dto.request.partyMember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostPartyMemberRequestDto {
    private String userEmail;
    private int partyId;
}
