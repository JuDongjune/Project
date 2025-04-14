package com.example.myproject.Repository;
import com.example.myproject.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,String> {
    Optional<Todo> findByTodoId(String todoId);
    List<Todo> findAllByTodoContent(String todoCotent);
    List<Todo> findAllByUser_UserId(String userId);
}
