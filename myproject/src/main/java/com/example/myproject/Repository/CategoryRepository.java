package com.example.myproject.Repository;
import com.example.myproject.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findAllByUser_UserId(String userUserId);
}
