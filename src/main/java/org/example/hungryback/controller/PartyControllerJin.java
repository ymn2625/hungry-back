package org.example.hungryback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.party.PostPartyRequestDto;
import org.example.hungryback.dto.request.partyMember.PatchMemberRoleRequestDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.message.GetMessagesResponseDto;
import org.example.hungryback.dto.response.party.GetPartiesByUserEmailResponseDto;
import org.example.hungryback.dto.response.party.GetPartiesResponseDto;
import org.example.hungryback.dto.response.party.GetPartyResponseDto;
import org.example.hungryback.dto.response.party.PostPartyResponseDto;
import org.example.hungryback.dto.response.partyMember.*;
import org.example.hungryback.service.MessageService;
import org.example.hungryback.service.PartyMemberService;
import org.example.hungryback.service.PartyMemberServiceJin;
import org.example.hungryback.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PartyControllerJin {
    final PartyService partyService;
    final MessageService messageService;
    final PartyMemberServiceJin partyMemberServiceJin;


    @GetMapping("/parties/{partyId}/party-member-jin")
    public ResponseEntity<? super GetPartyMembersResponseDtoJin> getPartyMembersJin(@PathVariable Integer partyId) {
        ResponseEntity<? super GetPartyMembersResponseDtoJin> response = partyMemberServiceJin.getPartyMembersJin(partyId);

        return response;
    }

}
