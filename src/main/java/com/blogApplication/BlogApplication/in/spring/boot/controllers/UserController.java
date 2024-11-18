package com.blogApplication.BlogApplication.in.spring.boot.controllers;

import com.blogApplication.BlogApplication.in.spring.boot.dto.UserDto;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.ApiResponse;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.UserResponse;
import com.blogApplication.BlogApplication.in.spring.boot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
       UserDto createUserDto = userService.createUser(userDto);
       return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id){
        UserDto getUserDto = userService.getUserById(id);
        return new ResponseEntity<>(getUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/allusers")
    public ResponseEntity<UserResponse> getAllUser(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
      UserResponse list = userService.getAllUser(pageNumber, pageSize);
      return new ResponseEntity<UserResponse>(list, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updatedUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id){
        UserDto updatedUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }


}
