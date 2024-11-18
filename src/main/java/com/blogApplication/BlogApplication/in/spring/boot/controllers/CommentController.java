package com.blogApplication.BlogApplication.in.spring.boot.controllers;


import com.blogApplication.BlogApplication.in.spring.boot.dto.CommentDto;
import com.blogApplication.BlogApplication.in.spring.boot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // get method for the comment

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createdComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
      CommentDto createComment = commentService.createComment(commentDto, postId);
      return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/post/{postId}")
    public String deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return "Comment deleted successfully !";
    }
}
