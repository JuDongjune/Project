package com.example.myproject.Service;

import com.example.myproject.Dto.CategoryDto;
import com.example.myproject.Dto.CategoryUpdateDto;
import com.example.myproject.Entity.Category;
import com.example.myproject.Repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    //카테고리 생성
    public void createCategory(String userId,CategoryDto categoryDto) {
        //사용자 id 받아오기
        String categoryName = categoryDto.getCategoryName();
        //카테고리명 검증
        if ( categoryRepository.existsByCategoryName(categoryDto.getCategoryName(), Long.valueOf(userId))) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }
        else if (categoryName.isEmpty()) throw new IllegalArgumentException("카테고리명을 입력해주세요.");

        Category category = new Category();
        category.setUserId(Long.valueOf(userId));
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCreatedDt(categoryDto.getCreateDt());
        category.setUpdatedDt(categoryDto.getUpdateDt());

        categoryRepository.save(category);
    }

    //카테고리 수정
    public void updateCategory(String userId,String categoryId, CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findByUserIdAndCategoryId(userId,categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        String categoryName = categoryUpdateDto.getCategoryName();

        if ( categoryRepository.existsByCategoryName(categoryUpdateDto.getCategoryName(), Long.valueOf(userId))) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        }
        else if (categoryName.isEmpty()) throw new IllegalArgumentException("카테고리명을 입력해주세요.");

        category.setCategoryName(categoryUpdateDto.getCategoryName());
        category.setUpdatedDt(categoryUpdateDto.getUpdateDt());

        categoryRepository.save(category);
    }
    //카테고리 삭제
    public void deleteCategory(String userId, String categoryId) {
        Category category = categoryRepository.findByUserIdAndCategoryId(userId,categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        categoryRepository.delete(category);
    }
    //카테고리 조회
    public List<CategoryDto> getCategory(String userId) {//List 형식으로 반환되므로 List로 선언 필요
        Optional<Category> categories = categoryRepository.findAllByUserId(Long.valueOf(userId));
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
