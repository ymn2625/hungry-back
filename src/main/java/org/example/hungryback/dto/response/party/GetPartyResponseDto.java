package org.example.hungryback.dto.response.party;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.repository.resultSet.GetPartyResultSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetPartyResponseDto extends ResponseDto {
    private int partyId;
    private int partyCount;
    private int partyLimit;
    private String partyName;
    private String partyDescription;
    private String partyImg;
    private String partyTime;
    private String partyStartTime;
    private String partyEndTime;
    private int storeId;
    private String storeName;
    private GetPartyResponseDto (GetPartyResultSet resultSet) {
        super();
        this.partyId = resultSet.getPartyId();
        this.partyCount = resultSet.getPartyCount();
        this.partyLimit = resultSet.getPartyLimit();
        this.partyName = resultSet.getPartyName();
        this.partyDescription = resultSet.getPartyDescription();
        this.partyImg = resultSet.getPartyImg();
        this.partyTime = resultSet.getPartyTime();
        this.partyStartTime = resultSet.getPartyStartTime();
        this.partyEndTime = resultSet.getPartyEndTime();
        this.storeId = resultSet.getStoreId();
        this.storeName = resultSet.getStoreName();
    }

    public static ResponseEntity<GetPartyResponseDto> success (GetPartyResultSet resultSet) {
        GetPartyResponseDto responseBody = new GetPartyResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistStore() {
        ResponseDto  responseBody = new ResponseDto(ResponseCode.NOT_EXIST_STORE, ResponseMessage.NOT_EXIST_STORE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
