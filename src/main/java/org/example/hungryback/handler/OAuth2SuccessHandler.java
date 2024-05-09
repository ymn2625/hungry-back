package org.example.hungryback.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.entity.RefreshTokenEntity;
import org.example.hungryback.provider.JwtProvider;
import org.example.hungryback.entity.CustomOAuth2User;
import org.example.hungryback.repository.RefreshTokenRepository;
import org.example.hungryback.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String userEmail = oAuth2User.getName();

        if(userRepository.findByUserEmail(userEmail) == null) {
            response.sendRedirect("http://localhost:3000/account/sign-up-oauth/" + userEmail);
        } else {
            String token = jwtProvider.createToken(userEmail);
            String refreshToken = jwtProvider.createRefreshToken(userEmail);
            RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(refreshToken, userEmail);
            refreshTokenRepository.save(refreshTokenEntity);
            jwtProvider.refreshTokenCookie(response, refreshToken);

            response.sendRedirect("http://localhost:3000/account/oauth-response/" + token + "/360000");
        }
    }
}
