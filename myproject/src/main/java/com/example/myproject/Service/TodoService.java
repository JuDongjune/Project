package com.example.myproject.Service;
import com.example.myproject.Dto.TodoDto;
import com.example.myproject.Dto.TodoStatus;
import com.example.myproject.Dto.TodoUpdateDto;
import com.example.myproject.Entity.Todo;
import com.example.myproject.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    //투두 생성
    public void createTodo(String userId, String CategoryId, TodoDto todoDto) {
        String todoCotent = todoDto.getTodoContent();
        String todoId = todoDto.getTodoId();
        //투두 내용 검증
        if ( todoRepository.existsByTodoContent(CategoryId, Long.valueOf(userId), todoId, todoCotent)) {
            throw new IllegalArgumentException("이미 존재하는 내용의 일정입니다.");
        }
        else if (todoCotent.isEmpty()) throw new IllegalArgumentException("등록하려는 일정을 입력해주세요.");

        Todo todo = new Todo();
        todo.setTodoId(todoDto.getTodoId());
        todo.setUserId(Long.valueOf(userId));
        todo.setTodoId(todo.getTodoId());

        todo.setTodoContent(todoDto.getTodoContent());
        todo.setCreatedDt(todoDto.getCreateDt());
        todo.setDueDate(todoDto.getDueDate());

        todo.setIsDone(todo.getIsDone());
    }

    //투두 수정
    public void updateTodo(String userId,String categoryId, String todoId, TodoUpdateDto todoUpdateDto) {
        Todo todo = todoRepository.findByUserIdandCategoryId(Long.valueOf(userId), categoryId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        if(todoRepository.existsByTodoContent(categoryId,Long.valueOf(userId),todoId,todoUpdateDto.getTodoContent())){
            throw new IllegalArgumentException("이미 존재하는 일정입니다.");
        }

        else if(todoUpdateDto.getTodoContent().isEmpty()){
            throw new IllegalArgumentException("일정을 입력하세요.");
        }
        todo.setTodoContent(todoUpdateDto.getTodoContent());
        todo.setUpdatedDt(todoUpdateDto.getUpadteDt());
    }
    //투두 상태 변경
    public void updateTodoSatus(String todoId, TodoStatus status){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        todo.changeStatus(status);
    }
    //투두 삭제
    public void deleteTodo(String userId, String categoryId,String todoId) {
        Todo todo = todoRepository.findByUserIdandCategoryIdandTodoId(Long.valueOf(userId),categoryId,todoId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        todoRepository.delete(todo);
    }
    //투두 조회
    public List<TodoDto> getTodo(String userId,String CategoryId) {//List 형식으로 반환되므로 List로 선언 필요
        Optional<Todo> todos = todoRepository.findByUserIdandCategoryId(Long.valueOf(userId),CategoryId);
        return todos.stream() //List 꺼내서 반복
                .map(todo -> {
                    TodoDto todoDto = new TodoDto();

                    todoDto.setTodoId(String.valueOf(todo.getTodoId()));
                    todoDto.setUserId(todo.getUserId());

                    todoDto.setTodoId(todo.getTodoId());
                    todoDto.setTodoContent(todo.getTodoContent());
                    todoDto.setIsDone(todo.getIsDone());

                    todoDto.setDueDate(todo.getDueDate());
                    todoDto.setCreateDt(todo.getCreatedDt());
                    todoDto.setUpdateDt(todo.getUpdatedDt());
                    return todoDto;
                }).collect(Collectors.toList());
    }

}
