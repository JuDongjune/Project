package com.example.myproject.Service;
import com.example.myproject.Dto.TodoDto;
import com.example.myproject.Dto.TodoStatus;
import com.example.myproject.Entity.Category;
import com.example.myproject.Entity.Todo;
import com.example.myproject.Entity.User;
import com.example.myproject.Repository.CategoryRepository;
import com.example.myproject.Repository.TodoRepository;
import com.example.myproject.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Builder
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    //투두 생성
    public void createTodo(String userId, String categoryId, TodoDto todoDto) {
        String todoCotent = todoDto.getTodoContent();
        String todoId = todoDto.getTodoId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));

        //투두 내용 검증
        List<Todo> todos = todoRepository.findAllByTodoContent(todoCotent);

        boolean isDuplicate = todos.stream() //todoContent로 내용 전부 가져와서 비교
                .anyMatch(todo ->
                        todo.getUser().getUserId().equals(userId) &&
                                todo.getCategory().getCategoryId().equals(categoryId));

        if (isDuplicate) {
            throw new IllegalArgumentException("이미 등록된 Todo 입니다.");
        } else if (todoCotent.isEmpty()) throw new IllegalArgumentException("등록하려는 일정을 입력해주세요.");

        Todo todo = new Todo();
        todo.setUser(user);
        todo.setCategory(category);

        todo.setTodoId(todo.getTodoId());
        todo.setDueDate(todoDto.getDueDate());
        todo.setIsDone(TodoStatus.WAIT); // 기본 상태 대기
    }

    //투두 수정
    public void updateTodo(String userId, String categoryId, String todoId, TodoDto todoDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));

        //투두 내용 검증
        String todoContent = todoDto.getTodoContent();
        Optional<Todo> todoOptional = todoRepository.findByTodoId(todoId);
        List<Todo> todos = todoRepository.findAllByTodoContent(todoContent);

        boolean isDuplicate = todos.stream() //todoContent로 내용 전부 가져와서 비교
                .anyMatch(todo ->
                        todo.getUser().getUserId().equals(userId) &&
                                todo.getCategory().getCategoryId().equals(categoryId));

        if (isDuplicate) {
            throw new IllegalArgumentException("이미 등록된 Todo 입니다.");
        } else if (todoContent.isEmpty()) throw new IllegalArgumentException("등록하려는 일정을 입력해주세요.");
        Todo todo = new Todo();
        todo.setTodoId(todoDto.getTodoId());

        todo.setTodoContent(todoContent);
        todo.setDueDate(todoDto.getDueDate());
        todo.setIsDone(TodoStatus.WAIT);
    }

    //투두 상태 변경
    public void updateTodoSatus(String userId, String categoryId, String todoId, TodoStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        todo.changeStatus(status);
    }

    //투두 삭제
    public void deleteTodo(String userId, String categoryId, String todoId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));
        Todo todo = todoRepository.findByTodoId(todoId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        todoRepository.delete(todo);
    }

    //투두 조회
    @Transactional
    public List<TodoDto> getTodo(String userId, String categoryId) {//List 형식으로 반환되므로 List로 선언 필요
        //사용자별 + 카테고리별 투두 조회
        List<Todo> todos = todoRepository.findAllByUser_UserId(userId).stream()
                    .filter(todo -> todo.getCategory().getCategoryId().equals(categoryId)).toList();

        return todos.stream()
                .map(todo -> new TodoDto(
                        todo.getTodoId(),
                        todo.getCategory().getCategoryId(),
                        todo.getUser().getUserId(),
                        todo.getTodoContent(),
                        todo.getIsDone(),
                        todo.getCreatedDt(),
                        todo.getUpdatedDt(),
                        todo.getDueDate())).toList();

    }
}
