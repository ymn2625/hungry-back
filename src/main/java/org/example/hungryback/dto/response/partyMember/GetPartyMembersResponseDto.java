package org.example.hungryback.dto.response.partyMember;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.object.PartyMemberListItem;
import org.example.hungryback.repository.resultSet.GetPartyMemberListResultSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.example.hungryback.dto.object.PartyMemberListItem.copyList;

@Getter
public class GetPartyMembersResponseDto extends ResponseDto {
    List<PartyMemberListItem> partyMemberList;
    private GetPartyMembersResponseDto(List<GetPartyMemberListResultSet> resultSets) {
        super();
        partyMemberList = copyList(resultSets);
    }

    public static ResponseEntity<GetPartyMembersResponseDto> success(List<GetPartyMemberListResultSet> resultSets) {
        GetPartyMembersResponseDto responseBody = new GetPartyMembersResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistParty() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_PARTY, ResponseMessage.NOT_EXIST_PARTY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
