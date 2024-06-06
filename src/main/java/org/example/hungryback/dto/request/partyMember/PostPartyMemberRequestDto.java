package org.example.hungryback.dto.request.partyMember;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostPartyMemberRequestDto {
    @Email
    @NotBlank
    private String userEmail;
    private int partyId;
}
