package com.example.myproject.Service;

import com.example.myproject.Entity.User;
import com.example.myproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(request);

        Map<String, Object> attributes = user.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        User findUser = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(User.of(email)));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "id"
        );
    }
}
