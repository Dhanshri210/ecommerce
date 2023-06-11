package com.bikkadit.ecommerce.serviceimpl;

import com.bikkadit.ecommerce.entity.Category;
import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.repository.CategoryRepository;
import com.bikkadit.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    //Create Category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        logger.info("Request Create For Create Category");
        Category category = this.modelMapper.map(categoryDto , Category.class);
        Category save= categoryRepository.save(category);
        logger.info("Request Completed For Create Category");
        return modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        return null;
    }

    @Override
    public void deleteCategory(String categoryId) {

    }

    @Override
    public PageableResponse<CategoryDto> getAllCategory() {
        return null;
    }

    @Override
    public CategoryDto getsingle(String categoryId) {
        return null;
    }
}
