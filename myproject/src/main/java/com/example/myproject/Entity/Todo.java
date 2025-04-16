package com.example.myproject.Entity;

import com.example.myproject.Dto.TodoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "todolist")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @Column(name = "todo_id",length = 20,nullable = false)
    private String  todoId;

    @Column(name = "todo_content",length = 45)
    private String  todoContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_isdone",length = 10)
    private TodoStatus isDone;
    // 상태 변경 메서드
    public void changeStatus(TodoStatus isDone) {
        this.isDone = isDone;
    }
    @Column(name = "user_id",length = 20,nullable = false)
    private Long userId;

    @Column(name = "category_id",length = 20,nullable = false)
    private Long categoryId;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

    @Column(name = "due_date")
    private LocalDateTime dueDate;
}
