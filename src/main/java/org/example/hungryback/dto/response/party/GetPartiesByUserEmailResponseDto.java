package org.example.hungryback.dto.response.party;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.object.PartyByUserEmailListItem;
import org.example.hungryback.dto.object.PartyListItem;
import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.repository.resultSet.GetPartyByUserEmailResultSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetPartiesByUserEmailResponseDto extends ResponseDto {

    private List<PartyByUserEmailListItem> partyListItem;

    private GetPartiesByUserEmailResponseDto(List<GetPartyByUserEmailResultSet> resultSets) {
        super();
        this.partyListItem = PartyByUserEmailListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetPartiesByUserEmailResponseDto> success(List<GetPartyByUserEmailResultSet> resultSets) {
        GetPartiesByUserEmailResponseDto responseBody = new GetPartiesByUserEmailResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
