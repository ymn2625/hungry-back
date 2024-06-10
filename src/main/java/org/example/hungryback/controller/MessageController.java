package org.example.hungryback.controller;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.message.SendMessageRequestDto;
import org.example.hungryback.dto.response.message.SendMessageResponseDto;
import org.example.hungryback.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final MessageService messageService;

    @MessageMapping("/message")
    public ResponseEntity<Void> receiveMessage(@RequestBody SendMessageRequestDto dto) {
        SendMessageResponseDto message = messageService.postMessage(dto);
        simpMessageSendingOperations.convertAndSend("/sub/parties/"+message.getPartyId(), message);
        return ResponseEntity.ok().build();
    }
}
