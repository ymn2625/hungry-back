package org.example.hungryback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.repository.resultSet.GetPartyByUserEmailResultSet;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartyByUserEmailListItem {
    private String content;
    private int partyId;
    private int partyCount;
    private int partyLimit;
    private String partyName;
    private String partyDescription;
    private String partyImg;
    private String partyTime;
    private String partyStartTime;
    private String partyEndTime;
    private int storeId;


    public PartyByUserEmailListItem(GetPartyByUserEmailResultSet resultSet) {
        this.content = resultSet.getContent();
        this.partyId = resultSet.getPartyId();
        this.partyCount = resultSet.getPartyCount();
        this.partyLimit = resultSet.getPartyLimit();
        this.partyName = resultSet.getPartyName();
        this.partyDescription = resultSet.getPartyDescription();
        this.partyImg = resultSet.getPartyImg();
        this.partyTime = resultSet.getPartyTime();
        this.partyStartTime = resultSet.getPartyStartTime();
        this.partyEndTime = resultSet.getPartyEndTime();
        this.storeId = resultSet.getStoreId();
    }

    public static List<PartyByUserEmailListItem> copyList(List<GetPartyByUserEmailResultSet> resultSets) {
        List<PartyByUserEmailListItem> list = new ArrayList<>();
        for(GetPartyByUserEmailResultSet resultSet : resultSets) {
            PartyByUserEmailListItem partyByUserEmailListItem = new PartyByUserEmailListItem(resultSet);
            list.add(partyByUserEmailListItem);
        }
        return list;
    }
}
