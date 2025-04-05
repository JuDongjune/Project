package com.example.myproject.Service;

import com.example.myproject.Dto.UserDto;
import com.example.myproject.Entity.User;
import com.example.myproject.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 등록
    public UserDto saveUser(UserDto userDto) {
        User user = new User(null, userDto.getName(), userDto.getEmail());
        User saved = userRepository.save(user);
        return new UserDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    // 유저 조회
    public UserDto getUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
    }
}