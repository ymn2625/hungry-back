package org.example.hungryback.dto.response.user;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {
    private String token;
    private int expirationTime;

    private SignInResponseDto(String token) {
        super();
        this.token = token;
        this.expirationTime = 3600000;
    }

    public static ResponseEntity<SignInResponseDto> success(String token) {
        SignInResponseDto responseBody = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
