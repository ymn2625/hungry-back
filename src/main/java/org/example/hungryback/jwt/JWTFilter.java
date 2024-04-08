package org.example.hungryback.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.config.SecurityUserDTO;
import org.example.hungryback.user.entity.UserEntity;
import org.example.hungryback.user.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {

        String token = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (!StringUtils.hasText(token)) {
            System.out.println("token null");
            filterChain.doFilter(request, response);

            return;
        }

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            return;
        }

        UserEntity existUser = userRepository.findByEmail(jwtUtil.getEmail(token));
        if(existUser == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }

        SecurityUserDTO securityUserDTO = new SecurityUserDTO(existUser);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                securityUserDTO, null, List.of(new SimpleGrantedAuthority(securityUserDTO.getRole()))
        );
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}