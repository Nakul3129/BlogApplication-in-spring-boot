package com.blogApplication.BlogApplication.in.spring.boot.repositories;

import com.blogApplication.BlogApplication.in.spring.boot.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
}
