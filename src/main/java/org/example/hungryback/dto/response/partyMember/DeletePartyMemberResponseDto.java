package org.example.hungryback.dto.response.partyMember;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DeletePartyMemberResponseDto extends ResponseDto {
    private DeletePartyMemberResponseDto() {
        super();
    }

    public static ResponseEntity<DeletePartyMemberResponseDto> success() {
        DeletePartyMemberResponseDto responseBody = new DeletePartyMemberResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistPartyMember() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_PARTY_MEMBER, ResponseMessage.NOT_EXIST_PARTY_MEMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
