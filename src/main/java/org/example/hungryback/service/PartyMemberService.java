package org.example.hungryback.service;

import org.example.hungryback.dto.request.partyMember.PatchMemberRoleRequestDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.partyMember.GetPartyMembersResponseDto;
import org.example.hungryback.dto.response.partyMember.PatchMemberRoleResponseDto;
import org.example.hungryback.dto.response.partyMember.DeletePartyMemberResponseDto;
import org.example.hungryback.dto.response.partyMember.PostPartyMemberResponseDto;
import org.springframework.http.ResponseEntity;

public interface PartyMemberService {
    ResponseEntity<? super PostPartyMemberResponseDto> postPartyMember(PostPartyMemberRequestDto dto);
    ResponseEntity<? super DeletePartyMemberResponseDto> deletePartyMember(String userEmail, String email, Integer partyId);
    ResponseEntity<? super PatchMemberRoleResponseDto> patchMemberRole(PatchMemberRoleRequestDto dto, String email);
    ResponseEntity<? super GetPartyMembersResponseDto> getPartyMembers(String userEmail, String email, Integer partyId);
}
