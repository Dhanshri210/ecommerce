package com.bikkadit.ecommerce.serviceimpl;

import com.bikkadit.ecommerce.constant.AppConstant;
import com.bikkadit.ecommerce.entity.Category;
import com.bikkadit.ecommerce.entity.User;
import com.bikkadit.ecommerce.exception.ResourceNotFoundException;
import com.bikkadit.ecommerce.helper.Helpers;
import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.UserDto;
import com.bikkadit.ecommerce.repository.CategoryRepository;
import com.bikkadit.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${category.profile.image.path}")
    private String imageUploadPath1;

    private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    //Create Category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        logger.info("Request Create For Create Category");
        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savecat = categoryRepository.save(category);
        logger.info("Request Completed For Create Category");
        return modelMapper.map(savecat, CategoryDto.class);
    }

    //Update category
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        logger.info("Request Created For Update Category : {}" , categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCoverImage());
        category.setCoverImage(categoryDto.getCoverImage());
        Category update = categoryRepository.save(category);
        logger.info("Request Completed For Update Category : {}" , categoryId);
        return modelMapper.map(update, CategoryDto.class);
    }

    //Delete Category
    @Override
    public void deleteCategory(String categoryId) {
        logger.info("Request Created For Delete Category : {}" , categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.CATEGORY_DELETE));
        String fullpath = imageUploadPath1 +category.getCoverImage();
        try {
            Path path = Paths.get(fullpath);
                Files.delete(path);
                logger.info("Image User Deleted :{}", categoryId);
            } catch (IOException e) {
            logger.info("Request Completed For Delete cover imgae : {}", categoryId);
            }
            categoryRepository.delete(category);
        logger.info("Request Completed For Delete Category : {}" , categoryId);
    }

    //Get All Category
    @Override
    public PageableResponse<CategoryDto> getAllCategory
    (Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        logger.info("Request Created For Fetch All Category With Page Number");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ?
                (Sort.by(sortBy).descending())
                : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> response = Helpers.getPageableResponse(page, CategoryDto.class);
        logger.info("Request Completed For Fetch All Category with page Numbers");
        return response;
    }

    // Get Single Category By ID
    @Override
    public CategoryDto getsingle(String categoryId) {
        logger.info("Request Created For Fetch Single Category : {}" ,categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.FETCH_CATEGORY));
        logger.info("Request Completed For Fetch Single Category : {}" ,categoryId);
        return modelMapper.map(category, CategoryDto.class);
    }
}