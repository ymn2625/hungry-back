package org.example.hungryback.handler;

import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.provider.JwtProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    private final JwtProvider jwtProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        List<String> authorizationHeader = headerAccessor.getNativeHeader("Authorization");
        StompCommand command = headerAccessor.getCommand();

        if(command.equals(StompCommand.CONNECT)) {
            if (authorizationHeader == null) {
                // 헤더 없을 때 에러
                throw new MalformedJwtException("jwt");
            }

            //token 분리
            String token = "";
            String authorizationHeaderStr = authorizationHeader.get(0).replace("[","").replace("]","");
            if (authorizationHeaderStr.startsWith("Bearer ")) {
                token = authorizationHeaderStr.replace("Bearer ", "");
            } else {
                // 토큰 형식 안맞을 때 에러
                throw new MalformedJwtException("jwt");
            }

            // 토큰 유효성 검사
            if(!jwtProvider.isValid(token)) {
                // 유효하지 않으면 에러
                throw new MalformedJwtException("jwt");
            }
        }
        if (command.equals(StompCommand.ERROR)) {
            System.out.println("stomp");
            throw new MessageDeliveryException("error");
        }

        return message;
    }

}
