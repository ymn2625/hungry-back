package org.example.hungryback.dto.response.account;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchProfileImgResponseDto extends ResponseDto {

    private PatchProfileImgResponseDto () {
        super();
    }

    public static ResponseEntity<PatchProfileImgResponseDto> success() {
        PatchProfileImgResponseDto responseBody = new PatchProfileImgResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EIXST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
