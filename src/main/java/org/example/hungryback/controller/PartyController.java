package org.example.hungryback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.party.PostPartyRequestDto;
import org.example.hungryback.dto.request.partyMember.PatchMemberRoleRequestDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.message.GetMessagesResponseDto;
import org.example.hungryback.dto.response.party.GetPartiesResponseDto;
import org.example.hungryback.dto.response.party.PatchMemberRoleResponseDto;
import org.example.hungryback.dto.response.party.PostPartyResponseDto;
import org.example.hungryback.dto.response.partyMember.DeletePartyMemberResponseDto;
import org.example.hungryback.dto.response.partyMember.PostPartyMemberResponseDto;
import org.example.hungryback.service.MessageService;
import org.example.hungryback.service.PartyMemberService;
import org.example.hungryback.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PartyController {
    final PartyService partyService;
    final MessageService messageService;
    final PartyMemberService partyMemberService;

    @PostMapping("/stores/{storeId}/party")
    public ResponseEntity<? super PostPartyResponseDto> postParty(@RequestBody @Valid PostPartyRequestDto requestBody) {
        ResponseEntity<? super PostPartyResponseDto> response = partyService.postParty(requestBody);
        return response;
    }

    @GetMapping("/stores/{storeId}/parties")
    public ResponseEntity<? super GetPartiesResponseDto> getPartiesByStoreId(@PathVariable("storeId") Integer storeId) {
        ResponseEntity<? super GetPartiesResponseDto> response = partyService.getParties(storeId);
        return response;
    }

    @GetMapping("/{userEmail}/parties")
    public ResponseEntity<? super GetPartiesResponseDto> getPartiesByUserEmail(@PathVariable("userEmail") String userEmail, @AuthenticationPrincipal String email) {
        ResponseEntity<? super GetPartiesResponseDto> response = partyService.getPartiesByUserEmail(userEmail, email);
        return response;
    }

    @GetMapping("/{userEmail}/parties/{partyId}")
    public ResponseEntity<? super GetMessagesResponseDto> getMessages(@PathVariable("userEmail") String userEmail, @AuthenticationPrincipal String email, @PathVariable Integer partyId) {
        ResponseEntity<? super GetMessagesResponseDto> response = messageService.getMessages(userEmail, email, partyId);
        return response;
    }

    @PostMapping("/stores/{storeId}/party-member")
    public ResponseEntity<? super PostPartyMemberResponseDto> postPartyMember(@RequestBody @Valid PostPartyMemberRequestDto requestBody) {
        ResponseEntity<? super PostPartyMemberResponseDto> response = partyMemberService.postPartyMember(requestBody);
        return response;
    }

    @DeleteMapping("/{userEmail}/parties/{partyId}")
    public ResponseEntity<? super DeletePartyMemberResponseDto> deletePartyMember(@PathVariable("userEmail") String userEmail, @AuthenticationPrincipal String email, @PathVariable Integer partyId) {
        ResponseEntity<? super DeletePartyMemberResponseDto> response = partyMemberService.deletePartyMember(userEmail, email, partyId);
        return response;
    }

    @PatchMapping("/{userEmail}/parties/party-member")
    public ResponseEntity<? super PatchMemberRoleResponseDto> patchMemberRole(@RequestBody @Valid PatchMemberRoleRequestDto requestBody, @AuthenticationPrincipal String email) {
        ResponseEntity<? super PatchMemberRoleResponseDto> response = partyMemberService.patchMemberRole(requestBody, email);
        return response;
    }
}
