package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.response.message.GetMessagesResponseDto;
import org.example.hungryback.entity.MessageEntity;
import org.example.hungryback.repository.MessageRepository;
import org.example.hungryback.repository.PartyRepository;
import org.example.hungryback.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImplement implements MessageService {
    private final PartyRepository partyRepository;
    private final MessageRepository messageRepository;

    @Override
    public ResponseEntity<? super GetMessagesResponseDto> getMessages(String userEmail, String email, Integer partyId) {
        List<MessageEntity> resultSets = new ArrayList<>();
        try {
            if(!userEmail.equals(email)) return GetMessagesResponseDto.noPermission();

            boolean isExistParty = partyRepository.existsByPartyId(partyId);
            if(!isExistParty) return GetMessagesResponseDto.noExistParty();

            resultSets = messageRepository.findByPartyIdOrderBySendTime(partyId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMessagesResponseDto.success(resultSets);
    }

}
