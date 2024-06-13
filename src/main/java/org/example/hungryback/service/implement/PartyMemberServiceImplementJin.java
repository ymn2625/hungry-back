package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.partyMember.PatchMemberRoleRequestDto;
import org.example.hungryback.dto.request.partyMember.PostPartyMemberRequestDto;
import org.example.hungryback.dto.response.partyMember.*;
import org.example.hungryback.entity.PartyEntity;
import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.repository.PartyMemberRepository;
import org.example.hungryback.repository.PartyMemberRepositoryJin;
import org.example.hungryback.repository.PartyRepository;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.repository.resultSet.GetPartyMemberResultSet;
import org.example.hungryback.service.PartyMemberService;
import org.example.hungryback.service.PartyMemberServiceJin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyMemberServiceImplementJin implements PartyMemberServiceJin {
    private final PartyMemberRepositoryJin partyMemberRepositoryJin;
    private final PartyRepository partyRepository;

    //진영만듦
    @Override
    public ResponseEntity<? super GetPartyMembersResponseDtoJin> getPartyMembersJin(Integer partyId) {
        List<GetPartyMemberResultSet> resultSets = new ArrayList<>();
        GetPartyMemberResultSet userProfile;
        try {
            boolean isExistParty = partyRepository.existsByPartyId(partyId);
            if(!isExistParty) return GetPartyMembersResponseDto.noExistParty();

            resultSets = partyMemberRepositoryJin.findPartyMemberByPartyId(partyId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetPartyMembersResponseDtoJin.success(resultSets);
    }


}
