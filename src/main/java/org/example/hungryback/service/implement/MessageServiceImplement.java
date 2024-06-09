package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.ResponseDto;
import org.example.hungryback.dto.request.message.SendMessageRequestDto;
import org.example.hungryback.dto.response.message.GetMessagesResponseDto;
import org.example.hungryback.dto.response.message.SendMessageResponseDto;
import org.example.hungryback.entity.MessageEntity;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.repository.MessageRepository;
import org.example.hungryback.repository.PartyMemberRepository;
import org.example.hungryback.repository.PartyRepository;
import org.example.hungryback.repository.UserRepository;
import org.example.hungryback.repository.resultSet.GetMessageResultSet;
import org.example.hungryback.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImplement implements MessageService {
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetMessagesResponseDto> getMessages(String userEmail, Integer partyId) {
        List<GetMessageResultSet> resultSets = new ArrayList<>();
        try {
            boolean isExistParty = partyRepository.existsByPartyId(partyId);
            if(!isExistParty) return GetMessagesResponseDto.noExistParty();

            boolean isPartyMember = partyMemberRepository.existsByUserEmailAndPartyId(userEmail, partyId);
            if(!isPartyMember) return GetMessagesResponseDto.noPermission();

            resultSets = messageRepository.findMessagesByPartyId(partyId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMessagesResponseDto.success(resultSets);
    }

    @Override
    public SendMessageResponseDto postMessage(SendMessageRequestDto dto) {
        MessageEntity messageEntity = new MessageEntity(dto);
        messageRepository.save(messageEntity);
        int messageId = messageEntity.getMessageId();

        SendMessageResponseDto sendMessageResponseDto = new SendMessageResponseDto(dto, messageId);

        return sendMessageResponseDto;
    }

}
