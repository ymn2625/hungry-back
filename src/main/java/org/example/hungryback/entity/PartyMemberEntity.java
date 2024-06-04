package org.example.hungryback.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.entity.primaryKey.PartyMemberPk;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="partyMember")
@Table(name="partyMember")
@IdClass(PartyMemberPk.class)
public class PartyMemberEntity {
    @Id
    private String userEmail;
    @Id
    private int partyId;
    private int memberRole;

    public PartyMemberEntity (String userEmail, int partyId, int memberRole) {
        this.userEmail = userEmail;
        this.partyId = partyId;
        this.memberRole = memberRole;
    }
}
