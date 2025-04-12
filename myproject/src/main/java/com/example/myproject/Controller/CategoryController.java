package com.example.myproject.Controller;

import com.example.myproject.Dto.CategoryDto;
import com.example.myproject.Dto.CategoryUpdateDto;
import com.example.myproject.Service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController("/api/category")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "category API", description = "카테고리 관련 API")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "카테고리 조회",description = "카테고리 조회")
    @GetMapping("/api/todo/category/View/{userid}/{categoryid}")
    public ResponseEntity viewCategory(
            @PathVariable String userid){
        categoryService.getCategory(userid);
        return ResponseEntity.ok("카테고리 조회 완료!");
    }

    @Operation(summary = "카테고리 등록",description = "카테고리를 등록합니다.")
    @PostMapping("/api/todo/category/Reg/{userid}/{categoryid}")
    private ResponseEntity<String> createCategory(
            @PathVariable String userId
            ,@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(userId,categoryDto);
        return ResponseEntity.ok("카테고리 생성 완료!");
    }
    @Operation(summary = "카테고리 수정",description = "카테고리를 수정합니다.")
    @PutMapping("/api/todo/category/Edit/{userid}/{categoryid}")
    private ResponseEntity<String> updateCategory(
            @PathVariable String userId
            ,@PathVariable String categoryid
            ,@RequestBody CategoryUpdateDto categoryUpdateDto
            ,@RequestHeader("Authorization") String token){
        categoryService.updateCategory(userId,categoryid,categoryUpdateDto);
        return ResponseEntity.ok("카테고리 수정 완료");
    }

    @Operation(summary = "카테고리 삭제",description = "카테고리를 삭제합니다.")
    @DeleteMapping("/api/todo/category/Del/{userid}/{categoryid}")
    private ResponseEntity<String> deleteCategory(
            @PathVariable String userId
            ,@PathVariable String categoryId
    ){
        categoryService.deleteCategory(userId,categoryId);
        return ResponseEntity.ok("카테고리 삭제 완료");
    }
}
