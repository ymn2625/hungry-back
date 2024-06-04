package org.example.hungryback.service.implement;

import org.example.hungryback.dto.request.party.PartyRequestDto;
import org.example.hungryback.dto.response.party.PartyResponseDto;
import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.repository.PartyRepository;
import org.example.hungryback.service.PartyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartyServiceImplement implements PartyService {

    private final PartyRepository partyRepository;

    public PartyServiceImplement(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Override
    public List<PartyResponseDto> partyList(PartyRequestDto dto) {
        Integer storeId = Integer.parseInt(dto.getStoreId());

        List<PartyEntity> parties = partyRepository.findByPartyStoreId(storeId);
        // PartyEntity를 PartyResponseDto로 변환하여 반환합니다.
        return parties.stream()
                .map(this::mapToPartyResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public PartyResponseDto partyInfo(PartyRequestDto dto) {
        Integer partyId = Integer.parseInt(dto.getPartyId());
        PartyEntity party = partyRepository.findByPartyId(partyId);
        // StoreEntity를 DTO로 변환
        PartyResponseDto responseDto = convertEntityToDto(party);
        return responseDto;
    }

    // 엔티티를 DTO로 변환하는 메서드
    private PartyResponseDto convertEntityToDto(PartyEntity party) {
        PartyResponseDto dto = new PartyResponseDto();
        dto.setPartyId(party.getPartyId());
        dto.setPartyStoreId(party.getPartyStoreId());
        dto.setPartyName(party.getPartyName());
        dto.setPartyHost(party.getPartyHost());
        dto.setPartyLimitNum(party.getPartyLimitNum());
        dto.setPartyDescription(party.getPartyDescription());
        dto.setPartyCreatedDay(party.getPartyCreatedDay());
        dto.setPartyPayingState(party.getPartyPayingState());
        dto.setPartyExitedDay(party.getPartyExitedDay());
        // 나머지 필드들도 엔티티에서 가져와서 DTO에 설정

        return dto;
    }


    private PartyResponseDto mapToPartyResponseDto(PartyEntity partyEntity) {
        return new PartyResponseDto(
                partyEntity.getPartyId(),
                partyEntity.getPartyStoreId(),
                partyEntity.getPartyName(),
                partyEntity.getPartyHost(),
                partyEntity.getPartyLimitNum(),
                partyEntity.getPartyDescription(),
                partyEntity.getPartyCreatedDay(),
                partyEntity.getPartyPayingState(),
                partyEntity.getPartyExitedDay()
        );
    }
}
