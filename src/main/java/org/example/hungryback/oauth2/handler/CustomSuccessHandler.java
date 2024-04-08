package org.example.hungryback.oauth2.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hungryback.jwt.JWTUtil;
import org.example.hungryback.oauth2.dto.CustomOAuth2User;
import org.example.hungryback.user.common.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //OAuth2User
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String accessToken = jwtUtil.createAccessToken(customOAuth2User.getEmail(), customOAuth2User.getRole());
        String refreshToken = jwtUtil.createRefreshToken(customOAuth2User.getEmail(), customOAuth2User.getRole());

        response.addCookie(createCookie("AccessToken", accessToken));
        response.addCookie(createCookie("RefreshToken", refreshToken));

        if(customOAuth2User.getRole().equals(Role.ROLE_GUEST)) {
            response.sendRedirect("http://localhost:3000/");
        }
        else {
            response.sendRedirect("http://localhost:3000/");
            // dmdkfjkdjksl
        }

    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}