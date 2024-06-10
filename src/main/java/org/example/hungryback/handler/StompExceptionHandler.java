package org.example.hungryback.handler;

import io.jsonwebtoken.MalformedJwtException;
import org.example.hungryback.common.ResponseCode;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.charset.StandardCharsets;

@Component
public class StompExceptionHandler extends StompSubProtocolErrorHandler {
    public StompExceptionHandler() {
        super();
    }

    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
        String errorMessage = getErrorMessage(ex);
        accessor.setMessage(errorMessage);
        accessor.setLeaveMutable(true);

        return MessageBuilder.createMessage(errorMessage.getBytes(StandardCharsets.UTF_8), accessor.getMessageHeaders());
    }

    private String getErrorMessage(Throwable ex) {
        Throwable cause = ex.getCause() != null ? ex.getCause() : ex;

        if (cause instanceof MalformedJwtException) {
            return ResponseCode.INVALID_TOKEN;
        } else if (cause instanceof AccessDeniedException) {
            return ResponseCode.NO_PERMISSION;
        } else if (cause instanceof IllegalArgumentException) {
            return ResponseCode.VALIDATION_FAIL;
        } else if (cause instanceof MessageDeliveryException) {
            return ResponseCode.MESSAGE_DELIVERY_ERROR;
        } else {
            return ResponseCode.UNEXPECTED_MESSAGE_ERROR;
        }
    }
}
