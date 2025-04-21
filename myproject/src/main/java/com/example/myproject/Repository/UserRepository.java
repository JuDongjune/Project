package com.example.myproject.Repository;

import com.example.myproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String userId);
    Optional<User> findByEmail(String email);
    boolean existsByNickname(String nickname);

}