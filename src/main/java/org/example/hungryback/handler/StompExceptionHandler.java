package org.example.hungryback.handler;

import org.example.hungryback.common.ResponseCode;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
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
        if(ex.getCause().getMessage().equals("jwt")) {
            return jwtException(clientMessage, ex);
        }

        if(ex.getCause().getMessage().equals("error")) {
            return messageException(clientMessage, ex);
        }

        return super.handleClientMessageProcessingError(clientMessage, ex);
    }

    //메시지 예외
    private Message<byte[]> messageException(Message<byte[]> clientMessage, Throwable ex) {
        return errorMessage(ResponseCode.INVALID_MESSAGE);
    }

    //jwt 예외
    private Message<byte[]> jwtException(Message<byte[]> clientMessage, Throwable ex) {
        return errorMessage(ResponseCode.INVALID_TOKEN);
    }

    //메시지 생성
    private Message<byte[]> errorMessage(String responseCode) {
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);

        accessor.setMessage(String.valueOf(responseCode));
        accessor.setLeaveMutable(true);

        return MessageBuilder.createMessage(responseCode.getBytes(StandardCharsets.UTF_8), accessor.getMessageHeaders());
    }
}
