package org.example.hungryback.dto.response.auth;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SendCertificationResponseDto extends ResponseDto {
    private SendCertificationResponseDto() {
        super();
    }

    public static ResponseEntity<SendCertificationResponseDto> success() {
        SendCertificationResponseDto responseBody = new SendCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> messageSendFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.MESSAGE_FAIL, ResponseMessage.MESSAGE_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
