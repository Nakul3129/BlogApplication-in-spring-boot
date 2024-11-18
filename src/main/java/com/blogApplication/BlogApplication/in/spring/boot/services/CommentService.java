package com.blogApplication.BlogApplication.in.spring.boot.services;

import com.blogApplication.BlogApplication.in.spring.boot.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    String deleteComment(Integer commentId);
}
