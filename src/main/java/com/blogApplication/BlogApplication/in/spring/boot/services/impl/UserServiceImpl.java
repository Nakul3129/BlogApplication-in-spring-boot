package com.blogApplication.BlogApplication.in.spring.boot.services.impl;

import com.blogApplication.BlogApplication.in.spring.boot.dto.UserDto;
import com.blogApplication.BlogApplication.in.spring.boot.entities.UserEntity;
import com.blogApplication.BlogApplication.in.spring.boot.exceptions.ResourceNotFoundException;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.UserResponse;
import com.blogApplication.BlogApplication.in.spring.boot.repositories.UserRepo;
import com.blogApplication.BlogApplication.in.spring.boot.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
         UserEntity userEntity = dtoToUser(userDto);
         UserEntity savedUser = userRepo.save(userEntity);
         return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        UserEntity userEntity = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "Id", userId));
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAbout(userDto.getAbout());
        userEntity.setPassword(userDto.getPassword());

      UserEntity updatedUser =  userRepo.save(userEntity);
      UserDto updatedDto = userToDto(updatedUser);
        return updatedDto;

    }

    @Override
    public UserDto getUserById(Integer userId) {
        UserEntity userEntity = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(userEntity);
    }

    @Override
    public UserResponse getAllUser(Integer pageNumber, Integer pageSize) {

//        int pageNumber = 1;
//        int pageSize = 5;
        Pageable page = PageRequest.of( pageNumber,  pageSize);
        Page<UserEntity> userPage = userRepo.findAll(page);
       // List<UserEntity> userEntity = userRepo.findAll();
        List<UserEntity> allUser = userPage.getContent();
      List<UserDto> userDtos =  allUser.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

      UserResponse userResponse = new UserResponse();
      userResponse.setContent(userDtos);
      userResponse.setPageNumber(userPage.getNumber());
      userResponse.setPageSize(userPage.getSize());
      userResponse.setTotalPages(userPage.getTotalPages());
      userResponse.setTotalElements(userPage.getTotalPages());
      userResponse.setLastPage(userPage.isLast());

        return userResponse;
    }

    @Override
    public String deleteUser(Integer userId) {
    UserEntity userEntity = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        userRepo.delete(userEntity);
        return "User deleted successfully";
    }

    private UserEntity dtoToUser(UserDto user){
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);


//         userEntity.setId(user.getId());
//         userEntity.setName(user.getName());
//         userEntity.setAbout(user.getAbout());
//         userEntity.setPassword(user.getPassword());
//         userEntity.setEmail(user.getEmail());

         return userEntity;
    }

    public UserDto userToDto(UserEntity userEntity){
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
//        userDto.setId(userEntity.getId());
//        userDto.setName(userEntity.getName());
//        userDto.setAbout(userEntity.getAbout());
//        userDto.setPassword(userEntity.getPassword());
//        userDto.setEmail(userEntity.getEmail());

        return userDto;
    }
}
