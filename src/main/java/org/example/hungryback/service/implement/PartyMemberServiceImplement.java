package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.partyMember.DeletePartyMemberResponseDto;
import org.example.hungryback.dto.response.partyMember.PostPartyMemberResponseDto;
import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.repository.PartyMemberRepository;
import org.example.hungryback.repository.PartyRepository;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.service.PartyMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartyMemberServiceImplement implements PartyMemberService {
    private final PartyMemberRepository partyMemberRepository;
    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<? super PostPartyMemberResponseDto> postPartyMember(PostPartyMemberRequestDto dto) {
        String userEmail = dto.getUserEmail();
        Integer partyId = dto.getPartyId();

        try {
            boolean isExistUser = userRepository.existsByUserEmail(userEmail);
            if(!isExistUser) return PostPartyMemberResponseDto.noExistUser();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        PartyMemberEntity partyMemberEntity = partyMemberRepository.findByUserEmailAndPartyId(userEmail, partyId);
        if(partyMemberEntity != null) {
            return PostPartyMemberResponseDto.duplicatedPartyMember();
        } else {
            partyMemberEntity = new PartyMemberEntity(userEmail, partyId, 0);
            partyMemberRepository.save(partyMemberEntity);
        }

        PartyEntity partyEntity = partyRepository.findByPartyId(400);
        partyEntity.patchPartyCount(1);
        partyRepository.save(partyEntity);

        return PostPartyMemberResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super DeletePartyMemberResponseDto> deletePartyMember(String userEmail, String email, Integer partyId) {
        try {
            if(!userEmail.equals(email)) return DeletePartyMemberResponseDto.noPermission();

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.databaseError();
        }

        PartyMemberEntity partyMemberEntity = partyMemberRepository.findByUserEmailAndPartyId(userEmail, partyId);
        if(partyMemberEntity == null) return DeletePartyMemberResponseDto.noExistPartyMember();
        partyMemberRepository.delete(partyMemberEntity);

        PartyEntity partyEntity = partyRepository.findByPartyId(partyId);
        partyEntity.patchPartyCount(-1);
        partyRepository.save(partyEntity);

        return DeletePartyMemberResponseDto.success();
    }
}
