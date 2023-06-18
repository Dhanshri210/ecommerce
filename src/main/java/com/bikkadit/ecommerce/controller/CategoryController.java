package com.bikkadit.ecommerce.controller;

import com.bikkadit.ecommerce.constant.AppConstant;
import com.bikkadit.ecommerce.helper.ApiResponse;
import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.UserDto;
import com.bikkadit.ecommerce.service.CategoryService;
import com.bikkadit.ecommerce.serviceimpl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Create Category
     *
     * @param categoryDto
     *
     * @return
     */

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        logger.info("Request Created For Create Category");
        CategoryDto catDto = categoryService.createCategory(categoryDto);
        logger.info("Request Completed For Create Category");
        return new ResponseEntity<>(catDto, HttpStatus.CREATED);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Update Category
     *
     * @param categoryId
     *
     * @return
     */

    @PutMapping("/updates/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable("categoryId")
                                                          String categoryId,@RequestBody CategoryDto categoryDto) {
        logger.info("Request Created For Update User : {}"  ,  categoryId);
        CategoryDto update = categoryService.updateCategory(categoryDto,categoryId);
        logger.info("Request Completed For Update User :{}" ,   categoryId);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Delete Category
     *
     * @param categoryId
     *
     * @return
     */

    @DeleteMapping("/deletes/{categoryId}")
    public ResponseEntity<ApiResponse> deleteUser
            (@PathVariable String categoryId) {
        logger.warn("Request Created For Delete User : {}",  categoryId);
        categoryService.deleteCategory(categoryId);
        ApiResponse message = ApiResponse
                .builder()
                .message(AppConstant.CATEGORY_DELETE)
                .success(true)
                .status(HttpStatus.OK).build();
        logger.warn("Request Completed For Update User : {}", categoryId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Get All Category
     *
     * @param CategoryDto
     *
     * @return
     */

    @GetMapping("/getAllCategory")
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
        logger.info("All Category Are Fetch Successfully");
        return new ResponseEntity<PageableResponse<CategoryDto>>
                (categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Get Single category
     *
     * @param categoryId
     *
     * @return
     */

    @GetMapping("/getSinglecat/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId) {
        logger.info("Single Users Fetch Successfully : {}"  , categoryId);
        return new ResponseEntity<>(categoryService.getsingle(categoryId)
                , HttpStatus.OK);
    }

}
