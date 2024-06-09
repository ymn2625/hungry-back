package org.example.hungryback.service;

import org.example.hungryback.dto.request.party.PostPartyRequestDto;
import org.example.hungryback.dto.response.party.GetPartiesResponseDto;
import org.example.hungryback.dto.response.party.PostPartyResponseDto;
import org.springframework.http.ResponseEntity;

public interface PartyService {
    ResponseEntity<? super PostPartyResponseDto> postParty (PostPartyRequestDto dto);
    ResponseEntity<? super GetPartiesResponseDto> getParties (Integer storeId);
    ResponseEntity<? super GetPartiesResponseDto> getPartiesByUserEmail (String userEmail);

}
