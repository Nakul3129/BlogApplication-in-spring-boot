package com.blogApplication.BlogApplication.in.spring.boot.repositories;

import com.blogApplication.BlogApplication.in.spring.boot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
