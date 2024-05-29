package org.example.hungryback.dto.request.party;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//파티 삭제할 때 보내줄 DTO
@Getter
@Setter
@NoArgsConstructor
public class PartyDeleteDTO {

    private Long partyId;
}
