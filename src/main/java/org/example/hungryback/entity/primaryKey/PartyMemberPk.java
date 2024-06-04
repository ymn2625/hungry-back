package org.example.hungryback.entity.primaryKey;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PartyMemberPk implements Serializable {
    @Column(name = "party_id")
    private int partyId;
    @Column(name = "user_email")
    private String userEmail;
}
