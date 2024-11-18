package com.blogApplication.BlogApplication.in.spring.boot.services.impl;

import com.blogApplication.BlogApplication.in.spring.boot.dto.PostDto;
import com.blogApplication.BlogApplication.in.spring.boot.entities.CategoryEntity;
import com.blogApplication.BlogApplication.in.spring.boot.entities.PostEntity;
import com.blogApplication.BlogApplication.in.spring.boot.entities.UserEntity;
import com.blogApplication.BlogApplication.in.spring.boot.exceptions.ResourceNotFoundException;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.PostResponse;
import com.blogApplication.BlogApplication.in.spring.boot.repositories.CategoryRepo;
import com.blogApplication.BlogApplication.in.spring.boot.repositories.PostRepo;
import com.blogApplication.BlogApplication.in.spring.boot.repositories.UserRepo;
import com.blogApplication.BlogApplication.in.spring.boot.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));


       PostEntity createdPost = modelMapper.map(postDto, PostEntity.class);
       createdPost.setImageName("default.png");
       createdPost.setAddedDate(new Date());
       createdPost.setUser(user);
       createdPost.setCategory(category);

       PostEntity updatedPost =  postRepo.save(createdPost);

       return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
      PostEntity post =  postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
      post.setTitle(postDto.getTitle());
      post.setContent(postDto.getContent());
      post.setImageName(postDto.getImageName());
      PostEntity updatedPost = postRepo.save(post);
      return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
     PostEntity post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        postRepo.delete(post);
      //  return "Post deleted successfully";
    }

    @Override
    public PostDto getPostById(Integer postId) {
     PostEntity post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
     return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//          if(sortDir.equalsIgnoreCase("asc")){
//            sort = Sort.by(sortBy).ascending();
//          }else{
//              sort = Sort.by(sortBy).descending();
//          }

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<PostEntity> pagePost = postRepo.findAll(page);

        List<PostEntity> allPosts = pagePost.getContent();

   //  List<PostEntity> allPosts =   postRepo.findAll();

        List<PostDto> postDto = allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

          PostResponse postResponse = new PostResponse();

          postResponse.setContent(postDto);
          postResponse.setPageNumber(pagePost.getNumber());
          postResponse.setPageSize(pagePost.getSize());
          postResponse.setTotalElement(pagePost.getTotalElements());
          postResponse.setTotalPages(pagePost.getTotalPages());
          postResponse.setLastPage(pagePost.isLast());


        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        CategoryEntity cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
       List<PostEntity> posts =  postRepo.findByCategory(cat);
     List<PostDto> postDtos =   posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        List<PostEntity> posts =  postRepo.findByUser(user);
      List<PostDto> postDtos =  posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<PostEntity> gotPost = postRepo.findByTitleContaining(keyword);
      List<PostDto> collect = gotPost.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return collect;
    }
}
