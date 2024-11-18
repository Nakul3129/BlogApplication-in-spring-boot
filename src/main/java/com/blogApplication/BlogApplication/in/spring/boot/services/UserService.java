package com.blogApplication.BlogApplication.in.spring.boot.services;

import com.blogApplication.BlogApplication.in.spring.boot.dto.UserDto;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

     UserDto createUser(UserDto userDto);
     UserDto updateUser(UserDto userDto, Integer userId);
     UserDto getUserById(Integer userId);
     UserResponse getAllUser(Integer pageSize, Integer pageNumber);
     String deleteUser(Integer userId);
}
