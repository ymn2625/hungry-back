package org.example.hungryback.dto.response.auth;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CheckCertificationResponseDto extends ResponseDto{

    private String userEmail;

    private CheckCertificationResponseDto(String userEmail) {
        super();
        this.userEmail = userEmail;
    }

    public static ResponseEntity<CheckCertificationResponseDto> success(String userEmail) {
        CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto(userEmail);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> certificationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
