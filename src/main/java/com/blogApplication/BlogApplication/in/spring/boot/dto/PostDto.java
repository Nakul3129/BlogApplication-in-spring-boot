package com.blogApplication.BlogApplication.in.spring.boot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;


    private CategoryDto category;

     private UserDto user;

     private ArrayList<CommentDto> comment;
}
