package org.example.hungryback.dto.response.account;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserResponseDto extends ResponseDto {
    private String userEmail;
    private String userType;
    private String userName;
    private String userTel;
    private String userProfileImg;
    private String userNickname;

    private GetUserResponseDto(UserEntity userEntity) {
        super();
        this.userEmail = userEntity.getUserEmail();
        this.userType = userEntity.getUserType();
        this.userName = userEntity.getUserName();
        this.userTel = userEntity.getUserTel();
        this.userProfileImg = userEntity.getUserProfileImg();
        this.userNickname = userEntity.getUserNickname();
    }

    public static ResponseEntity<GetUserResponseDto> success (UserEntity userEntity) {
        GetUserResponseDto responseBody = new GetUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
