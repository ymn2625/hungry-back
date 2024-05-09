package org.example.hungryback.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.entity.CustomOAuth2User;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        UserEntity userEntity = null;
        String userEmail = null;

        if(oauthClientName.equals("kakao")) {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("kakao_account");
            userEmail = responseMap.get("email");
        }
        if(oauthClientName.equals("naver")) {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            userEmail = responseMap.get("email");
        }

        return new CustomOAuth2User(userEmail);
    }
}
