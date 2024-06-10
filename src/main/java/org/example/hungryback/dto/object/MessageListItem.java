package org.example.hungryback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.entity.MessageEntity;
import org.example.hungryback.repository.resultSet.GetMessageResultSet;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageListItem {
    private int messageId;
    private String userEmail;
    private String userNickname;
    private String userProfileImg;
    private int partyId;
    private String content;
    private String sendTime;

    public MessageListItem(GetMessageResultSet resultSet) {
        this.messageId = resultSet.getMessageId();
        this.userEmail = resultSet.getUserEmail();
        this.userNickname = resultSet.getUserNickname();
        this.userProfileImg = resultSet.getUserProfileImg();
        this.partyId = resultSet.getPartyId();
        this.content = resultSet.getContent();
        this.sendTime = resultSet.getSendTime();
    }

    public static List<MessageListItem> copyList(List<GetMessageResultSet> resultSets) {
        List<MessageListItem> list = new ArrayList<>();
        for(GetMessageResultSet resultSet : resultSets) {
            MessageListItem messageListItem = new MessageListItem(resultSet);
            list.add(messageListItem);
        }
        return list;
    }
}
