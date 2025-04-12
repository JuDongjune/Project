package com.example.myproject.Controller;

import com.example.myproject.Dto.CategoryDto;
import com.example.myproject.Dto.TodoDto;
import com.example.myproject.Dto.TodoStatus;
import com.example.myproject.Dto.TodoUpdateDto;
import com.example.myproject.Service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/Todo")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Todo API", description = "투두 관련 API")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "투두 조회",description = "일정 조회")
    @GetMapping("/api/todo/category/todo/View/{userid}/{categoryid}")
    public ResponseEntity viewTodo(
            @PathVariable String userid,
            @PathVariable String categoryId){
        todoService.getTodo(userid,categoryId);
        return ResponseEntity.ok("일정 조회 완료!");
    }

    @Operation(summary = "투두 등록",description = "일정을 등록합니다.")
    @PostMapping("/api/todo/category/todo/Reg/{userid}/{categoryid}")
    private ResponseEntity<String> createTodo(
            @PathVariable String userId
            ,@PathVariable String categoryId
            ,@RequestBody TodoDto todoDto) {
        todoService.createTodo(userId,categoryId,todoDto);
        return ResponseEntity.ok("일정 생성 완료!");
    }

    @Operation(summary = "투두 수정",description = "일정을 수정합니다.")
    @PutMapping("/api/todo/category/todo/Edit/{userid}/{categoryid}/{todoid}")
    private ResponseEntity<String> updateTodo(
            @PathVariable String userId
            ,@PathVariable String categoryId
            ,@PathVariable String todoId
            ,@RequestBody TodoUpdateDto todoUpdateDto){
        todoService.updateTodo(userId,categoryId,todoId,todoUpdateDto);
        return ResponseEntity.ok("일정 수정 완료");
    }

    @Operation(summary = "투두 상태 변경",description = "일정의 상태를 변경합니다.")
    @PatchMapping("/api/todo/category/todo/Edit/{userid}/{categoryid}/{todoid}")
    //상태만 변경되기 때문에 patch
    private ResponseEntity<String> updateTodoStatus(
            @PathVariable String userId,
            @PathVariable String categoryid,
            @PathVariable String todoid,
            @RequestParam TodoStatus status
            ){
        todoService.updateTodoSatus(todoid,status);
        return ResponseEntity.ok("일정 상태 변경 완료");
    }

    @Operation(summary = "투두 삭제",description = "일정을 삭제합니다.")
    @DeleteMapping("/api/todo/category/todo/Del/{userid}/{categoryid}/{todoid}")
    private ResponseEntity<String> deleteTodo(
            @PathVariable String userId
            ,@PathVariable String categoryId
            ,@PathVariable String todoId
    ){
        todoService.deleteTodo(userId, categoryId,todoId);
        return ResponseEntity.ok("일정 삭제 완료");
    }
}
