package com.blogApplication.BlogApplication.in.spring.boot.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;

    @NotBlank
    @Size(min = 4)
     private String categoryTitle;

    @NotBlank
    @Size(min = 12)
     private String categoryDescription;
}
