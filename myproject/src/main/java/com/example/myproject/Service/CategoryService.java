package com.example.myproject.Service;

import com.example.myproject.Dto.CategoryDto;
import com.example.myproject.Entity.Category;
import com.example.myproject.Entity.User;
import com.example.myproject.Repository.CategoryRepository;

import com.example.myproject.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    //카테고리 생성
    public void createCategory(String userId,CategoryDto categoryDto) {
        //사용자 id 받아오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        List<Category> categories = categoryRepository.findAllByUser_UserId(userId);

        //카테고리명 검증
        boolean isDuplicate = categories.stream().anyMatch(category -> category.getCategoryName().equals(categoryDto.getCategoryName()));
        if (isDuplicate) {
            throw new IllegalArgumentException("이미 존재하는 카테고리 입니다.");
        }
        else if(categories.isEmpty()){
            throw new IllegalArgumentException("카테고리명을 입력하세요.");
        }
        Category category = new Category();

        category.setUser(user);
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());

        categoryRepository.save(category);
    }

    //카테고리 수정
    public void updateCategory(String userId,String categoryId, CategoryDto categoryDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        List<Category> categories = categoryRepository.findAllByUser_UserId(userId);

        //카테고리명 검증
        boolean isDuplicate = categories.stream().anyMatch(category1 -> category.getCategoryName().equals(categoryDto.getCategoryName()));
        if (isDuplicate) {
            throw new IllegalArgumentException("이미 존재하는 카테고리 입니다.");
        }
        else if(categories.isEmpty()){
            throw new IllegalArgumentException("카테고리명을 입력하세요.");
        }

        category.setUser(user);
        category.setCategoryId(categoryDto.getCategoryId());

    }
    //카테고리 삭제
    public void deleteCategory(String userId, String categoryId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));

        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        categoryRepository.delete(category);
    }
    //카테고리 조회
    public List<CategoryDto> getCategory(String userId) {//List 형식으로 반환되므로 List로 선언 필요
        List<Category> categories = categoryRepository.findAllByUser_UserId(userId);
        return categories.stream() //List 꺼내서 반복
                .map(category -> {
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setCategoryId(category.getCategoryId());
                    categoryDto.setCategoryName(category.getCategoryName());
                    categoryDto.setCreateDt(category.getCreatedDt());
                    categoryDto.setUpdateDt(category.getUpdatedDt());
                    return categoryDto;
                }).collect(Collectors.toList());
    }
}
