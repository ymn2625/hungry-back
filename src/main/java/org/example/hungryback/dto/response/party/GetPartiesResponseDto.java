package org.example.hungryback.dto.response.party;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.object.PartyListItem;
import org.example.hungryback.entity.PartyEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetPartiesResponseDto extends ResponseDto {

    private List<PartyListItem> partyListFromServer;

    private GetPartiesResponseDto(List<PartyEntity> resultSets) {
        super();
        this.partyListFromServer = PartyListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetPartiesResponseDto> success(List<PartyEntity> resultSets) {
        GetPartiesResponseDto responseBody = new GetPartiesResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistStore() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_STORE, ResponseMessage.NOT_EXIST_STORE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
