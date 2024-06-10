package org.example.hungryback.repository.resultSet;

public interface GetMessageResultSet {
    int getMessageId();
    String getUserEmail();
    String getUserNickname();
    String getUserProfileImg();
    int getPartyId();
    String getContent();
    String getSendTime();
}
