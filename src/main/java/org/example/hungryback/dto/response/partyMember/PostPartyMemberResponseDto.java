package org.example.hungryback.dto.response.partyMember;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostPartyMemberResponseDto extends ResponseDto {
    private PostPartyMemberResponseDto() {
        super();
    }
    public static ResponseEntity<PostPartyMemberResponseDto> success() {
        PostPartyMemberResponseDto responseBody = new PostPartyMemberResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedPartyMember() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_PARTY_MEMBER, ResponseMessage.DUPLICATE_PARTY_MEMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
