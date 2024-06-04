package org.example.hungryback.dto.response.party;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.entity.PartyEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartyResponseDto extends ResponseDto {

    private int partyId;
    private int partyStoreId;
    private String partyName;
    private String partyHost;
    private int partyLimitNum;
    private String partyDescription;
    private String partyCreatedDay;
    private String partyPayingState;
    private String partyExitedDay;


    public static ResponseEntity<PartyResponseDto> success(PartyEntity partyEntity) {
        PartyResponseDto responseBody = new PartyResponseDto(
                partyEntity.getPartyId(),
                partyEntity.getPartyStoreId(),
                partyEntity.getPartyName(),
                partyEntity.getPartyHost(),
                partyEntity.getPartyLimitNum(),
                partyEntity.getPartyDescription(),
                partyEntity.getPartyCreatedDay(),
                partyEntity.getPartyPayingState(),
                partyEntity.getPartyExitedDay()
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> partyNotExist(PartyEntity partyEntity) {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EIXST_PARTY, ResponseMessage.NOT_EXIST_PARTY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
