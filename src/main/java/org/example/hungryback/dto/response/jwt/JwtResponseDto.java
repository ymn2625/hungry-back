package org.example.hungryback.dto.response.jwt;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class JwtResponseDto extends ResponseDto {
    private String token;
    private int expirationTime;

    private JwtResponseDto(String token) {
        super();
        this.token = token;
        this.expirationTime = 3600000;
    }

    public static ResponseEntity<JwtResponseDto> success(String token) {
        JwtResponseDto responseBody = new JwtResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> reissueFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.REISSUE_FAIL, ResponseMessage.REISSUE_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
