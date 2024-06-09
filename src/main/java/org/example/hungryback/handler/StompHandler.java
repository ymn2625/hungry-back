package org.example.hungryback.handler;

import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.entity.PartyMemberEntity;
import org.example.hungryback.provider.JwtProvider;
import org.example.hungryback.repository.PartyMemberRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    private final JwtProvider jwtProvider;
    private final PartyMemberRepository partyMemberRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        List<String> authorizationHeader = headerAccessor.getNativeHeader("Authorization");
        StompCommand command = headerAccessor.getCommand();

        if (command.equals(StompCommand.CONNECT)) {
            if (authorizationHeader == null) {
                throw new MalformedJwtException("Not exist header.");
            }

            String token = "";
            String authorizationHeaderStr = authorizationHeader.get(0).replace("[", "").replace("]", "");
            if (authorizationHeaderStr.startsWith("Bearer ")) {
                token = authorizationHeaderStr.replace("Bearer ", "");
            } else {
                throw new MalformedJwtException("Not token header");
            }

            if (!jwtProvider.isValid(token)) {
                throw new MalformedJwtException("Invalid token");
            }

            List<String> partyIdHeaders = headerAccessor.getNativeHeader("PartyId");
            if (partyIdHeaders == null || partyIdHeaders.isEmpty()) {
                throw new IllegalArgumentException("Party ID header is missing or empty");
            }

            String partyIdString = partyIdHeaders.get(0).replace("[", "").replace("]", "");
            System.out.println("partyIdString: " + partyIdString);

            String userEmail = jwtProvider.getUserEmail(token);
            int partyId = Integer.parseInt(partyIdString);

            PartyMemberEntity partyMemberEntity = partyMemberRepository.findByUserEmailAndPartyId(userEmail, partyId);
            System.out.println("partyMemberEntity: " + partyMemberEntity);
            if (partyMemberEntity == null) {
                throw new AccessDeniedException("User is not a member of the specified party");
            }
        }
        if (command.equals(StompCommand.ERROR)) {
            System.out.println("stomp");
            throw new MessageDeliveryException("Message error");
        }

        return message;
    }
}
