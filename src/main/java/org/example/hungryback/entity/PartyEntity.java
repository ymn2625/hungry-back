package org.example.hungryback.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "party")
public class PartyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partyId;
    private int partyStoreId;
    private String partyName;
    private String partyHost;
    private int partyLimitNum;
    private String partyDescription;
    private String partyCreatedDay;
    private String partyPayingState;
    private String partyExitedDay;

    @Builder
    public PartyEntity(int partyStoreId, String partyName, String partyHost, int partyLimitNum, String partyDescription, String partyCreatedDay, String partyPayingState, String partyExitedDay) {
        this.partyStoreId = partyStoreId;
        this.partyName = partyName;
        this.partyHost = partyHost;
        this.partyLimitNum = partyLimitNum;
        this.partyDescription = partyDescription;
        this.partyCreatedDay = partyCreatedDay;
        this.partyPayingState = partyPayingState;
        this.partyExitedDay = partyExitedDay;
    }

    @PrePersist
    protected void onCreate() {
        // 현재 날짜를 partyCreatedDay 필드에 자동으로 입력
        this.partyCreatedDay = LocalDate.now().toString();
    }
}
