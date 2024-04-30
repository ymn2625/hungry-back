package org.example.hungryback.entity.party;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hungryback.entity.UserEntity;

@Entity
@Getter
@Setter
@Table(name = "partyMember")
public class PartyMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partyId")
    private PartyEntity partyEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private UserEntity userEntity;
}
