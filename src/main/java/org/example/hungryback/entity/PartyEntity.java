package org.example.hungryback.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.dto.request.party.PostPartyRequestDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="party")
@Table(name="party")
public class PartyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partyId;
    private int partyCount;
    private int partyLimit;
    private String partyTitle;
    private String partyTime;
    private String partyStartTime;
    private String partyEndTime;
    private int storeId;

    public PartyEntity (PostPartyRequestDto dto) {
        this.partyCount = 1;
        this.partyLimit = dto.getPartyLimit();
        this.partyTitle = dto.getPartyTitle();
        this.partyTime = dto.getPartyTime();
        this.partyStartTime = dto.getPartyStartTime();
        this.partyEndTime = dto.getPartyEndTime();
        this.storeId = dto.getStoreId();
    }

    public void patchPartyCount(int count) {
        this.partyCount += count;
    }
}
