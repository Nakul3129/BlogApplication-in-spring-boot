package com.blogApplication.BlogApplication.in.spring.boot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Integer id;
    private String content;
 }
