package org.example.hungryback.dto.response.message;

import lombok.Getter;
import org.example.hungryback.common.ResponseCode;
import org.example.hungryback.common.ResponseMessage;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.object.MessageListItem;
import org.example.hungryback.entity.MessageEntity;
import org.example.hungryback.repository.resultSet.GetMessageResultSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetMessagesResponseDto extends ResponseDto {
    private List<MessageListItem> messageList;

    private GetMessagesResponseDto (List<GetMessageResultSet> resultSets) {
        super();
        this.messageList = MessageListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetMessagesResponseDto> success(List<GetMessageResultSet> resultSets) {
        GetMessagesResponseDto responseBody = new GetMessagesResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistParty() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_PARTY, ResponseMessage.NOT_EXIST_PARTY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
