package com.blogApplication.BlogApplication.in.spring.boot.services.impl;

import com.blogApplication.BlogApplication.in.spring.boot.dto.CommentDto;
import com.blogApplication.BlogApplication.in.spring.boot.entities.CommentEntity;
import com.blogApplication.BlogApplication.in.spring.boot.entities.PostEntity;
import com.blogApplication.BlogApplication.in.spring.boot.entities.UserEntity;
import com.blogApplication.BlogApplication.in.spring.boot.exceptions.ResourceNotFoundException;
import com.blogApplication.BlogApplication.in.spring.boot.repositories.CommentRepo;
import com.blogApplication.BlogApplication.in.spring.boot.repositories.PostRepo;
import com.blogApplication.BlogApplication.in.spring.boot.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;


    private UserEntity userEntity;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        PostEntity post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        CommentEntity comment = modelMapper.map(commentDto, CommentEntity.class);
        comment.setPost(post);


        CommentEntity savedComment = commentRepo.save(comment);
        CommentDto savedDto = modelMapper.map(savedComment, CommentDto.class);
        return savedDto;
    }

    @Override
    public String deleteComment(Integer commentId) {
     CommentEntity comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepo.delete(comment);
        return "Comment deleted successfully !";
    }
}
