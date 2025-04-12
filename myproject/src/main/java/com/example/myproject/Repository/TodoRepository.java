package com.example.myproject.Repository;
import com.example.myproject.Dto.TodoDto;
import com.example.myproject.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,String> {
    Optional<Todo> findByUserIdandCategoryId(Long userId, String categoryId);
    boolean existsByTodoContent(String categoryId, Long userId,String todoId,String todoContent);
    Optional<Todo> findAllByUserId(Long userId);
    Optional <Todo> findByUserIdandCategoryIdandTodoId(Long userId,String categoryId, String todoId);
}
