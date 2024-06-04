package org.example.hungryback.dto.response.party;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchMemberRoleResponseDto extends ResponseDto {
    private PatchMemberRoleResponseDto () {
        super();
    }

    public static ResponseEntity<PatchMemberRoleResponseDto> success() {
        PatchMemberRoleResponseDto responseBody = new PatchMemberRoleResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
