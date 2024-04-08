package org.example.hungryback.oauth2.service;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.oauth2.dto.CustomOAuth2User;
import org.example.hungryback.oauth2.dto.GoogleResponse;
import org.example.hungryback.oauth2.dto.NaverResponse;
import org.example.hungryback.oauth2.dto.OAuth2Response;
import org.example.hungryback.user.entity.UserEntity;
import org.example.hungryback.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        System.out.println(userRequest.getAccessToken().getTokenValue());

        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(attributes);
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(attributes);
        }
        else {
            return null;
        }

        String email = oAuth2Response.getProvider() + " " + oAuth2Response.getEmail();
        UserEntity existUser = userRepository.findByEmail(email);

        if (existUser == null) {
            UserEntity userEntity = new UserEntity(oAuth2Response, email);

            userRepository.save(userEntity);

            return new CustomOAuth2User(email, userEntity.getName(), userEntity.getRole());
        }
        else {
            return new CustomOAuth2User(email, existUser.getName(), existUser.getRole());
        }
    }
}