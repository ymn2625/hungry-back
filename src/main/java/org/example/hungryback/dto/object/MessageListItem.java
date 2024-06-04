package org.example.hungryback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.entity.MessageEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageListItem {
    private int messageId;
    private String userEmail;
    private int partyId;
    private String content;
    private String sendTime;

    public MessageListItem(MessageEntity messageEntity) {
        this.messageId = messageEntity.getMessageId();
        this.userEmail = messageEntity.getUserEmail();
        this.partyId = messageEntity.getPartyId();
        this.content = messageEntity.getContent();
        this.sendTime = messageEntity.getSendTime();
    }

    public static List<MessageListItem> copyList(List<MessageEntity> resultSets) {
        List<MessageListItem> list = new ArrayList<>();
        for(MessageEntity resultSet : resultSets) {
            MessageListItem messageListItem = new MessageListItem(resultSet);
            list.add(messageListItem);
        }
        return list;
    }
}
