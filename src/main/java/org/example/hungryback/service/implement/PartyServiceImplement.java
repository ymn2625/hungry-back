package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.party.PostPartyRequestDto;
import org.example.hungryback.dto.response.party.GetPartiesResponseDto;
import org.example.hungryback.dto.response.party.PostPartyResponseDto;
import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.repository.*;
import org.example.hungryback.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyServiceImplement implements PartyService {
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ResponseEntity<? super PostPartyResponseDto> postParty(PostPartyRequestDto dto) {

        String userEmail = dto.getUserEmail();
        int storeId = dto.getStoreId();

        try {
            boolean isExistUser = userRepository.existsByUserEmail(userEmail);
            if(!isExistUser) return PostPartyResponseDto.noExistUser();

            boolean isExistStore = storeRepository.existsByStoreId(storeId);
            if(!isExistStore) return PostPartyResponseDto.noExistStore();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        PartyEntity partyEntity = new PartyEntity(dto);
        partyRepository.save(partyEntity);

        PartyMemberEntity partyMemberEntity = new PartyMemberEntity(userEmail, partyEntity.getPartyId(), 1);
        partyMemberRepository.save(partyMemberEntity);

        return PostPartyResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetPartiesResponseDto> getParties(Integer storeId) {
        List<PartyEntity> resultSets = new ArrayList<>();
        try {
            boolean isExistStore = storeRepository.existsByStoreId(storeId);
            if(!isExistStore) return GetPartiesResponseDto.noExistStore();

            resultSets = partyRepository.findByStoreId(storeId);

            System.out.println(resultSets.get(0).getPartyName()+"파티이름은?");
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.databaseError();
        }
        return GetPartiesResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetPartiesResponseDto> getPartiesByUserEmail(String userEmail) {
        List<PartyEntity> resultSets = new ArrayList<>();
        try {
            boolean isExistUser = userRepository.existsByUserEmail(userEmail);
            if(!isExistUser) return GetPartiesResponseDto.noExistUser();

            resultSets = partyRepository.findByUserEmail(userEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetPartiesResponseDto.success(resultSets);
    }

}
