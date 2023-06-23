package com.bikkadit.ecommerce.service;

import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.PageableResponse;

public interface CategoryService {

    //Create Category
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update Category
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //Delete Category
    void deleteCategory(Integer categoryId);

    //Get All Category
    PageableResponse<CategoryDto> getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Get Single Category
    CategoryDto getsingle(Integer categoryId);

}
