package org.example.hungryback.service;

import org.example.hungryback.dto.request.party.PartyRequestDto;
import org.example.hungryback.dto.response.party.PartyResponseDto;

import java.util.List;

public interface PartyService {
    List<PartyResponseDto> partyList(PartyRequestDto dto);
    PartyResponseDto partyInfo(PartyRequestDto dto);
}
