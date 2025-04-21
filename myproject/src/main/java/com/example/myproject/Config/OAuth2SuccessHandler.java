package com.example.myproject.Config;

import com.example.myproject.Entity.User;
import com.example.myproject.Jwt.JwtUtil;
import com.example.myproject.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        User user = userRepository.findByEmail(email).orElseThrow();

        String token = jwtUtil.createToken(user);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
    }
}