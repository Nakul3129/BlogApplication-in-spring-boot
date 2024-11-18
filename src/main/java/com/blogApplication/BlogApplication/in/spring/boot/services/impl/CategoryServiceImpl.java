package com.blogApplication.BlogApplication.in.spring.boot.services.impl;

import com.blogApplication.BlogApplication.in.spring.boot.dto.CategoryDto;
import com.blogApplication.BlogApplication.in.spring.boot.entities.CategoryEntity;
import com.blogApplication.BlogApplication.in.spring.boot.exceptions.ResourceNotFoundException;
import com.blogApplication.BlogApplication.in.spring.boot.payloads.CategoryResponse;
import com.blogApplication.BlogApplication.in.spring.boot.repositories.CategoryRepo;
import com.blogApplication.BlogApplication.in.spring.boot.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       CategoryEntity cat = modelMapper.map(categoryDto, CategoryEntity.class);
       CategoryEntity savedCat = categoryRepo.save(cat);
        return modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        CategoryEntity cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
         categoryRepo.deleteById(categoryId);
         return "Category id deleted successfully";
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        CategoryEntity cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

    CategoryEntity savedCat = categoryRepo.save(cat);
        return modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        CategoryEntity cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<CategoryEntity> categoryPage = categoryRepo.findAll(page);

        List<CategoryEntity> list = categoryPage.getContent();
     List<CategoryDto> collect = list.stream().map((cat) -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(collect);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }
}
