package org.example.hungryback.dto.response.auth;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class FindAccountResponseDto extends ResponseDto {
    private String userProfileImg;
    private String userEmail;
    private String userType;
    private FindAccountResponseDto(UserEntity userEntity) {
        super();
        this.userEmail = userEntity.getUserEmail();
        this.userProfileImg = userEntity.getUserProfileImg();
        this.userType = userEntity.getUserType();
    }

    public static ResponseEntity<FindAccountResponseDto> success (UserEntity userEntity) {
        FindAccountResponseDto responseBody = new FindAccountResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
