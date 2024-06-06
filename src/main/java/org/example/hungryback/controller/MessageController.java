package org.example.hungryback.controller;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.dto.request.message.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/message")
    public ResponseEntity<Void> receiveMessage(@RequestBody MessageDto message) {
        simpMessageSendingOperations.convertAndSend("/sub/parties/"+message.getPartyId(), message);
        return ResponseEntity.ok().build();
    }
}
