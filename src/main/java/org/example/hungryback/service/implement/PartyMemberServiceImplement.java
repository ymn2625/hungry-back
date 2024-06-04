package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.partyMember.PatchMemberRoleRequestDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.party.PatchMemberRoleResponseDto;
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

        boolean isExistUser = userRepository.existsByUserEmail(userEmail);
        if(!isExistUser) return PostPartyMemberResponseDto.noExistUser();

        PartyMemberEntity partyMemberEntity = partyMemberRepository.findByUserEmailAndPartyId(userEmail, partyId);
        if(partyMemberEntity != null) {
            return PostPartyMemberResponseDto.duplicatedPartyMember();
        }

        partyMemberEntity = new PartyMemberEntity(userEmail, partyId, 0);
        partyMemberRepository.save(partyMemberEntity);

        PartyEntity partyEntity = partyRepository.findByPartyId(partyId);
        partyEntity.patchPartyCount(1);
        partyRepository.save(partyEntity);

        return PostPartyMemberResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super DeletePartyMemberResponseDto> deletePartyMember(String userEmail, String email, Integer partyId) {
        PartyMemberEntity partyMemberEntity = partyMemberRepository.findByUserEmailAndPartyId(userEmail, partyId);
        PartyEntity partyEntity = partyRepository.findByPartyId(partyId);

        if(!userEmail.equals(email)) return DeletePartyMemberResponseDto.noPermission();
        if(partyMemberEntity == null) return DeletePartyMemberResponseDto.noExistPartyMember();
        if(partyMemberEntity.getMemberRole() == 1) return DeletePartyMemberResponseDto.partyLeader();

        partyEntity.patchPartyCount(-1);

        partyMemberRepository.delete(partyMemberEntity);
        partyRepository.save(partyEntity);

        return DeletePartyMemberResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchMemberRoleResponseDto> patchMemberRole(PatchMemberRoleRequestDto dto, String email) {
        Integer partyId = dto.getPartyId();
        String userEmail = dto.getUserEmail();

        PartyMemberEntity partyLeader = partyMemberRepository.findByUserEmailAndPartyId(email, partyId);
        PartyMemberEntity partyMemberEntity = partyMemberRepository.findByUserEmailAndPartyId(userEmail, partyId);


        if(partyLeader == null) return PatchMemberRoleResponseDto.noPermission();
        if(partyLeader.getMemberRole() != 1) return PatchMemberRoleResponseDto.noPermission();
        if(partyMemberEntity == null) return PatchMemberRoleResponseDto.noExistUser();

        partyLeader.patchMemberRole(0);
        partyMemberEntity.patchMemberRole(1);

        partyMemberRepository.save(partyLeader);
        partyMemberRepository.save(partyMemberEntity);

        return PatchMemberRoleResponseDto.success();
    }


}
