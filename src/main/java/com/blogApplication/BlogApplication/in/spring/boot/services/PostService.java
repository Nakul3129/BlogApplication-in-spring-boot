package com.blogApplication.BlogApplication.in.spring.boot.services;

import com.blogApplication.BlogApplication.in.spring.boot.dto.PostDto;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.PostResponse;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostDto getPostById(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get post by category id

    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all post by user
    List<PostDto> getAllPostByUser(Integer userId);

    // get all post by search
    List<PostDto> searchPost(String keyword);
}
