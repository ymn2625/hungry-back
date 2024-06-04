package org.example.hungryback.dto.response.party;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostPartyResponseDto extends ResponseDto {
    private PostPartyResponseDto () {
        super();
    }

    public static ResponseEntity<PostPartyResponseDto> success() {
        PostPartyResponseDto responseBody = new PostPartyResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto  responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> noExistStore() {
        ResponseDto  responseBody = new ResponseDto(ResponseCode.NOT_EXIST_STORE, ResponseMessage.NOT_EXIST_STORE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
