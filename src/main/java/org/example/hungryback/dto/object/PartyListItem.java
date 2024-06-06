package org.example.hungryback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.entity.PartyEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartyListItem {
    private int partyId;
    private int partyCount;
    private int partyLimit;
    private String partyName;
    private String partyDescription;
    private String partyTime;
    private String partyStartTime;
    private String partyEndTime;
    private int storeId;

    public PartyListItem(PartyEntity partyEntity) {
        this.partyId = partyEntity.getPartyId();
        this.partyCount = partyEntity.getPartyCount();
        this.partyLimit = partyEntity.getPartyLimit();
        this.partyName = partyEntity.getPartyName();
        this.partyDescription = partyEntity.getPartyDescription();
        this.partyTime = partyEntity.getPartyTime();
        this.partyStartTime = partyEntity.getPartyStartTime();
        this.partyEndTime = partyEntity.getPartyEndTime();
        this.storeId = partyEntity.getStoreId();
    }

    public static List<PartyListItem> copyList(List<PartyEntity> resultSets) {
        List<PartyListItem> list = new ArrayList<>();
        for(PartyEntity resultSet : resultSets) {
            PartyListItem partyListItem = new PartyListItem(resultSet);
            list.add(partyListItem);
        }
        return list;
    }
}
