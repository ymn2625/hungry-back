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

    private JwtResponseDto(String token, int expirationTime) {
        super();
        this.token = token;
        // refresh 토큰 자동 재발급 체크하려고 짧게 설정해놓음
        this.expirationTime = expirationTime / 3000;
    }

    public static ResponseEntity<JwtResponseDto> success(String token, int expirationTime) {
        JwtResponseDto responseBody = new JwtResponseDto(token, expirationTime);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> reissueFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.REISSUE_FAIL, ResponseMessage.REISSUE_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
