package com.example.myproject.Service;

import com.example.myproject.Dto.UserDto;
import com.example.myproject.Entity.User;
import com.example.myproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(UserDto dto) {
        if (userRepository.existsByUserId(dto.getUserId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUserPw(dto.getUserPw());
        user.setUserName(dto.getUserName());
        user.setPhone(dto.getPhone());
        user.setNickname(dto.getNickname());
        user.setCreatedDt(LocalDateTime.now());
        user.setUpdatedDt(LocalDateTime.now());

        userRepository.save(user);
    }
}