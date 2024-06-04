package org.example.hungryback.service;

import org.example.hungryback.dto.response.message.GetMessagesResponseDto;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    ResponseEntity<? super GetMessagesResponseDto> getMessages (String userEmail, String email, Integer partyId);
}
