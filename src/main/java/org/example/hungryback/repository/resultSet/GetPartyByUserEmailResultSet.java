package org.example.hungryback.repository.resultSet;

public interface GetPartyByUserEmailResultSet {
    String getContent();
    int getPartyId();
    int getPartyCount();
    int getPartyLimit();
    String getPartyName();
    String getPartyDescription();
    String getPartyImg();
    String getPartyTime();
    String getPartyStartTime();
    String getPartyEndTime();
    int getStoreId();
}
