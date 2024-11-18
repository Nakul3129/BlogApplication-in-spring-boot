package com.blogApplication.BlogApplication.in.spring.boot.repositories;

import com.blogApplication.BlogApplication.in.spring.boot.entities.CategoryEntity;
import com.blogApplication.BlogApplication.in.spring.boot.entities.PostEntity;
import com.blogApplication.BlogApplication.in.spring.boot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByUser(UserEntity user);
    List<PostEntity> findByCategory(CategoryEntity category);

    List<PostEntity> findByTitleContaining(String title);
}
