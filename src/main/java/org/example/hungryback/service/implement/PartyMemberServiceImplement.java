package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.partyMember.PatchMemberRoleRequestDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.message.GetMessagesResponseDto;
import org.example.hungryback.dto.response.partyMember.GetPartyMembersResponseDto;
import org.example.hungryback.dto.response.partyMember.PatchMemberRoleResponseDto;
import org.example.hungryback.dto.response.partyMember.DeletePartyMemberResponseDto;
import org.example.hungryback.dto.response.partyMember.PostPartyMemberResponseDto;
import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.repository.PartyMemberRepository;
import org.example.hungryback.repository.PartyRepository;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.repository.resultSet.GetPartyMemberResultSet;
import org.example.hungryback.service.PartyMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<? super DeletePartyMemberResponseDto> deletePartyMember(String userEmail, Integer partyId) {
        PartyEntity partyEntity = partyRepository.findByPartyId(partyId);
        if(partyEntity == null) return DeletePartyMemberResponseDto.noExistParty();

        PartyMemberEntity partyMemberEntity = partyMemberRepository.findByUserEmailAndPartyId(userEmail, partyId);
        if(partyMemberEntity == null) return DeletePartyMemberResponseDto.noPermission();

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

    @Override
    public ResponseEntity<? super GetPartyMembersResponseDto> getPartyMembers(String userEmail, Integer partyId) {
        List<GetPartyMemberResultSet> resultSets = new ArrayList<>();
        GetPartyMemberResultSet userProfile;
        try {
            boolean isExistParty = partyRepository.existsByPartyId(partyId);
            if(!isExistParty) return GetPartyMembersResponseDto.noExistParty();

            boolean isPartyMember = partyMemberRepository.existsByUserEmailAndPartyId(userEmail, partyId);
            if(!isPartyMember) return GetPartyMembersResponseDto.noPermission();

            resultSets = partyMemberRepository.findPartyMembersByPartyId(partyId);
            userProfile = partyMemberRepository.findPartyMemberByPartyIdAndUserEmail(partyId, userEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetPartyMembersResponseDto.success(resultSets, userProfile);
    }


}
