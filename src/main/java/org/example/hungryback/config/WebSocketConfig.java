package org.example.hungryback.config;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.handler.StompExceptionHandler;
import org.example.hungryback.handler.StompHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final StompHandler stompHandler;
    private final StompExceptionHandler stompExceptionHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //stomp의 접속 주소
        registry.setErrorHandler(stompExceptionHandler)
                .addEndpoint("/ws")
                .addInterceptors()
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트의 send요청 처리
        registry.setApplicationDestinationPrefixes("/pub");
        //sub하는 클라이언트에게 메시지 전달
        registry.enableSimpleBroker("/sub");

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }
}
