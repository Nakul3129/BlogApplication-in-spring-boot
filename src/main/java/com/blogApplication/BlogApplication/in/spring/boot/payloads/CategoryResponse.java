package com.blogApplication.BlogApplication.in.spring.boot.payloads;

import com.blogApplication.BlogApplication.in.spring.boot.dto.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {
    private List<CategoryDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private int totalSizes;
    private boolean lastPage;
}
