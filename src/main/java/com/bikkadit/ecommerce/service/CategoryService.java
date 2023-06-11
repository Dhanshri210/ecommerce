package com.bikkadit.ecommerce.service;

import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.PageableResponse;

public interface CategoryService {

    //Create Category
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update Category
    CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);

    //Delete Category
    void deleteCategory(String categoryId);

    //Get All Category
    PageableResponse<CategoryDto> getAllCategory();

    //Get Single Category
    CategoryDto getsingle(String categoryId);

}
