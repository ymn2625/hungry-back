package org.example.hungryback.service;

import org.example.hungryback.dto.request.partyMember.PatchMemberRoleRequestDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.partyMember.*;
import org.springframework.http.ResponseEntity;

public interface PartyMemberServiceJin {
    ResponseEntity<? super GetPartyMembersResponseDtoJin> getPartyMembersJin(Integer partyId);
}
