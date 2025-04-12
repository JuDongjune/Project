package com.example.myproject.Repository;
import com.example.myproject.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String> {
    boolean existsByCategoryName(String categoryName, Long userId);
    Optional<Category> findByUserIdAndCategoryId(String userid, String categoryId);
    Optional<Category> findAllByUserId(Long userId);
}
