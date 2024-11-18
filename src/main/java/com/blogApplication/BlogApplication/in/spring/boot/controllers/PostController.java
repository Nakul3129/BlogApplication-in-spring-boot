package com.blogApplication.BlogApplication.in.spring.boot.controllers;

import com.blogApplication.BlogApplication.in.spring.boot.config.AppConstant;
import com.blogApplication.BlogApplication.in.spring.boot.dto.PostDto;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.PostResponse;
import com.blogApplication.BlogApplication.in.spring.boot.services.FileService;
import com.blogApplication.BlogApplication.in.spring.boot.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
   private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
   private ModelMapper modelMapper;

    // create type controller

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
     //   PostDto createdPostDto = modelMapper.map(createdPost, PostDto.class);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Integer categoryId){
             List<PostDto> allPostByCategory =  postService.getPostsByCategory(categoryId);
             return new ResponseEntity<List<PostDto>>(allPostByCategory, HttpStatus.OK);
    }



    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Integer userId){
      List<PostDto> allPostsByUser =  postService.getAllPostByUser(userId);
      return new ResponseEntity<List<PostDto>>(allPostsByUser, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir){
       PostResponse allPost = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
       return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
       PostDto getPost = postService.getPostById(postId);
       return new ResponseEntity<PostDto>(getPost, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return "post deleted successfully";
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
     PostDto updatedPost = postService.updatePost(postDto, postId);
     return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword){
       List<PostDto> searchedPost = postService.searchPost(keyword);
        return new ResponseEntity<List<PostDto>>(searchedPost, HttpStatus.OK);
    }

    // post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                         @PathVariable Integer postId) throws IOException {
        PostDto postDto = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, image);
       postDto.setImageName(fileName);
     PostDto updatedPost = postService.updatePost(postDto, postId);
      return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }


    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException{
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }


}
