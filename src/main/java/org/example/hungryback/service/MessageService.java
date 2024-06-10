package org.example.hungryback.service;

import org.example.hungryback.dto.request.message.SendMessageRequestDto;
import org.example.hungryback.dto.response.message.GetMessagesResponseDto;
import org.example.hungryback.dto.response.message.SendMessageResponseDto;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    ResponseEntity<? super GetMessagesResponseDto> getMessages (String userEmail, Integer partyId);
    SendMessageResponseDto postMessage(SendMessageRequestDto dto);
}
