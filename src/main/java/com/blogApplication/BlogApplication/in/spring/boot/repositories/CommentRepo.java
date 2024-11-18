package com.blogApplication.BlogApplication.in.spring.boot.repositories;

import com.blogApplication.BlogApplication.in.spring.boot.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity, Integer> {

}
