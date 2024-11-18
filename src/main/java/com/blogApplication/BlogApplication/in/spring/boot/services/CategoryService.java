package com.blogApplication.BlogApplication.in.spring.boot.services;

import com.blogApplication.BlogApplication.in.spring.boot.dto.CategoryDto;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.CategoryResponse;


public interface CategoryService {

    // create
        public CategoryDto createCategory(CategoryDto categoryDto);

    // delete
        public String deleteCategory(Integer categoryId);

    // update
        public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // get
        public CategoryDto getCategory(Integer categoryId);

    // getAll
        public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize);
}
