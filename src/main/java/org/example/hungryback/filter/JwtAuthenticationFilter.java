package org.example.hungryback.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.provider.JwtProvider;
import org.example.hungryback.entity.UserEntity;
import org.example.hungryback.repository.UserRepository;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 토큰 추출
        String token = parseBearerToken(request);
        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        // 토큰 유효 검사
        if(!jwtProvider.isValid(token)) {
            expiredTokenException(response);
            return;
        }

        // userId 추출
        String userEmail = jwtProvider.getUserEmail(token);

        // db에서 유저 정보 찾기
        UserEntity userEntity = userRepository.findByUserEmail(userEmail);
        String role = userEntity.getUserRole(); // role: ROLE_GUEST, ROLE_USER, ROLE_BEN, ROLE_ADMIN

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        // SecurityContext에 저장
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail, null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);

        filterChain.doFilter(request, response);
    }

    // 헤더에서 엑세스 토큰 얻기
    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if(!isBearer) return null;

        String token = authorization.substring(7);
        return token;
    }

    // 토큰 만료시 코드, 메시지
    public static void expiredTokenException(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"code\": \"ET\", \"message\" : \"Expired token.\"}");
    }
}

