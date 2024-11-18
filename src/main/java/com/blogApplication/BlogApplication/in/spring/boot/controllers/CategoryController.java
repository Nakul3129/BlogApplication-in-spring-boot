package com.blogApplication.BlogApplication.in.spring.boot.controllers;

import com.blogApplication.BlogApplication.in.spring.boot.dto.CategoryDto;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.CategoryResponse;
import com.blogApplication.BlogApplication.in.spring.boot.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        // create
            @PostMapping("/")
            public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
               CategoryDto createCategory = categoryService.createCategory(categoryDto);
               return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
            }

        // update
            @PutMapping("/{catId}")
            public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
              CategoryDto updateCategory = categoryService.updateCategory(categoryDto, catId);
              return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
            }

        // delete
            @DeleteMapping("/{catId}")
            public ResponseEntity<String> deleteCategory(@PathVariable Integer catId){
                categoryService.deleteCategory(catId);
                return new ResponseEntity<String>("Category deleted successfully", HttpStatus.OK);
            }

        // get
            @GetMapping("/{catId}")
            public ResponseEntity<CategoryDto> getCategory (@PathVariable Integer catId){
              CategoryDto getCategory1 = categoryService.getCategory(catId);
              return new ResponseEntity<CategoryDto>(getCategory1, HttpStatus.OK);
            }

        //getAll
            @GetMapping("/allCategory")
            public ResponseEntity<CategoryResponse> getAllCategory(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                                   @RequestParam(value = "pageSize", defaultValue = "0", required = false) Integer pageSize){
               CategoryResponse list = categoryService.getAllCategory(pageNumber, pageSize);
               return ResponseEntity.ok(list);
            }
}
