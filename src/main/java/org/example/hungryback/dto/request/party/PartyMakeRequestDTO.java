package org.example.hungryback.dto.request.party;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//파티 만들 때 정보 넣어 요청하는 DTO
@Getter
@Setter
@NoArgsConstructor
public class PartyMakeRequestDTO {

    private Long partyId;
    private String partyName;
    private Long partyLimit;
    private String partyDesc;
    private Date partyStartDate;
    private Date partyEndDate;
}
