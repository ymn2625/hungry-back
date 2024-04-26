package org.example.hungryback.entity.party;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "party")
public class PartyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partyId;
    @Column(unique = true)
    private String partyName;
    @Column(unique = true)
    private int partyLimit;
    private String partyDesc;

    @OneToMany(mappedBy = "partyEntity",cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PartyMemberEntity> partyMemberEntityList = new ArrayList<>();
}
