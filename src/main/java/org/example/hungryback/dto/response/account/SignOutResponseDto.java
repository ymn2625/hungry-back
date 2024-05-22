package org.example.hungryback.dto.response.account;

import lombok.Getter;
import org.example.hungryback.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignOutResponseDto extends ResponseDto {
    private SignOutResponseDto() {
        super();
    }

    public static ResponseEntity<SignOutResponseDto> success () {
        SignOutResponseDto responseBody = new SignOutResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
