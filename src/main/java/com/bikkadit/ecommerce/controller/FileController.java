package com.bikkadit.ecommerce.controller;

import com.bikkadit.ecommerce.entity.BaseEntity;
import com.bikkadit.ecommerce.helper.ImageResponse;
import com.bikkadit.ecommerce.payload.BaseEntityDto;
import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.ProductDto;
import com.bikkadit.ecommerce.payload.UserDto;
import com.bikkadit.ecommerce.service.CategoryService;
import com.bikkadit.ecommerce.service.FileService;
import com.bikkadit.ecommerce.service.ProductService;
import com.bikkadit.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class FileController extends BaseEntityDto {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Uploading Image
     *
     * @param userId
     *
     * @return
     */

    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("imageName")
                                                             MultipartFile image, @PathVariable String userId) throws IOException {
        logger.info("Request Created For Uploading user Image {}  ", userId);
        String images= fileService.uploadFile(image,imageUploadPath);
         UserDto user=userService.getUser(userId);
         user.setImageName(images);
         UserDto userdto =userService.updateUser(user,userId);
         ImageResponse response = ImageResponse
             .builder()
             .imageName(images)
             .success(true)
             .status(HttpStatus.CREATED)
             .build();
            logger.info("Request Completed For Uploading user Image {} ", userId);
           return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Serve Image
     *
     * @param userId
     *
     * @return
     */

        @GetMapping("/images/{userId}")
        public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto users= userService.getUser(userId);
        logger.info("user image name :{} ",users.getImageName());
        InputStream resource= fileService.getResource(imageUploadPath,users.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
        logger.info("Image Getting Successfully");
    }

    @PostMapping("/imagecover/{categoryId}")
    public ResponseEntity<ImageResponse> uploadCatImage(@RequestParam("imageName")
                                                         MultipartFile image, @PathVariable String categoryId) throws IOException {
        logger.info("Request Created For Uploading cover Image {} :",  categoryId);
        String images= fileService.uploadFile(image,imageUploadPath);
        CategoryDto cat=categoryService.getsingle(categoryId);
        cat.setCoverImage(images);
        CategoryDto categoryDto =categoryService.updateCategory(cat,categoryId);
        ImageResponse response = ImageResponse
                .builder()
                .imageName(images)
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
        logger.info("Request Completed For Uploading cover Image {}",  categoryId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/imagescovers/{categoryId}")
    public void serveCoverImage(@PathVariable String categoryId, HttpServletResponse response) throws IOException {
        CategoryDto cat= categoryService.getsingle(categoryId);
        logger.info("cover image name :{} ",cat.getCoverImage());
        InputStream resource= fileService.getResource(imageUploadPath,cat.getCoverImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
        logger.info("Image Getting Successfully");
    }

    @PostMapping("/productImage/{productId}")
    public ResponseEntity<ImageResponse> uploadProductImage(@RequestParam("imageName")
                                                        MultipartFile image, @PathVariable String productId) throws IOException {
        logger.info("Request Created For Uploading Product Image {} :",  productId);
        String products= fileService.uploadFile(image,imageUploadPath);
        ProductDto product=productService.getSingleProduct(productId);
        product.setProductImage(products);
        ProductDto productDto =productService.updateProduct(product,productId);
        ImageResponse response = ImageResponse
                .builder()
                .imageName(products)
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
        logger.info("Request Completed For Uploading Product Image {}",  productId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/productImages/{productId}")
    public void serveProductImage(@PathVariable String productId, HttpServletResponse response) throws IOException {
        ProductDto product= productService.getSingleProduct(productId);
        logger.info("Product image name :{} ",product.getProductImage());
        InputStream resource= fileService.getResource(imageUploadPath,product.getProductImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
        logger.info(" product Image Getting Successfully");
    }
}
