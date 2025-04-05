package com.example.myproject.Service;

import com.example.myproject.Dto.UserDto;
import com.example.myproject.Dto.UserLoginDto;
import com.example.myproject.Entity.User;
import com.example.myproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public User getUser(String userId){
        Optional<User> _user = userRepository.findById(userId);
        if(_user.isPresent()){
            return _user.get();
        } else{
            throw new RuntimeException("해당 유저가 없습니다.");
        }
    }

    public boolean login(UserLoginDto dto){
        Optional<User> _user = userRepository.findById(dto.getUserId());
        if(_user.isPresent()){
            if(_user.get().getUserPw().equals(dto.getUserPw())){
                return true;
            }
            else{
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }
        } else{
            throw new RuntimeException("해당 유저가 없습니다.");
        }
    }
}