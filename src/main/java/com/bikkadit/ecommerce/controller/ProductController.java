package com.bikkadit.ecommerce.controller;

import com.bikkadit.ecommerce.constant.AppConstant;
import com.bikkadit.ecommerce.helper.ApiResponse;
import com.bikkadit.ecommerce.helper.ImageResponse;
import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.ProductDto;
import com.bikkadit.ecommerce.service.FileService;
import com.bikkadit.ecommerce.service.ProductService;
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

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Value("${product.profile.image.path}")
    private String imageUploadPath2;

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Create Products
     *
     * @param productDto
     *
     * @return
     */

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        logger.info("Request Created For Create Products");
        ProductDto create = productService.create(productDto);
        logger.info("Request Completed For Create Products");
        return  new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Update ProductDetails
     *
     * @param productId
     *
     * @return
     */


    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId,@RequestBody ProductDto productDto){
        logger.info("Request Created For Update Products Details :{}",productId);
        ProductDto updateProduct = productService.updateProduct(productDto,productId);
        logger.info("Request Completed For Update Products Details :{}",productId);
        return  new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Delete Product
     *
     * @param productId
     *
     * @return
     */

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String productId) throws IOException {
        logger.info("Request Created For Delete Products Details :{}",productId);
       productService.deleteProduct(productId);
      ApiResponse response= ApiResponse.builder().message(AppConstant.DELETE_PRODUCT).status(HttpStatus.OK).success(true).build();
        logger.info("Request Completed For Delete Products Details :{}",productId);
   return new ResponseEntity<>(response,HttpStatus.OK);
    }
    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Get Single Product
     *
     * @param productId
     *
     * @return
     */

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId) {
        logger.info("Request Created For get Single Products Details :{}",productId);
        ProductDto get = productService.getSingleProduct(productId);
        logger.info("Request Completed For get Single Products Details :{}",productId);
        return new ResponseEntity<>(get, HttpStatus.OK);
    }
    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Get All Products
     *
     * @param productId
     *
     * @return
     */

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProducts(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir) {
        PageableResponse<ProductDto> response = productService.getAllProducts(pageNumber, pageSize, sortBy, sortDir);
        logger.info("All Products Are Fetch Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Get Product Live
     *
     * @param productDto
     *
     * @return
     */

    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir) {
        PageableResponse<ProductDto> response = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        logger.info("All Products Are Live Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Search Product by Query
     *
     * @param productDto
     *
     * @return
     */

    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProducts(@PathVariable String query,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir) {
        PageableResponse<ProductDto> response = productService.searchByTitle(query,pageNumber, pageSize, sortBy, sortDir);
        logger.info("Products Are get Successfully by using query : {}",query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/productImage/{productId}")
    public ResponseEntity<ImageResponse> uploadProductImage(@RequestParam("imageName")
                                                            MultipartFile image, @PathVariable String productId) throws IOException {
        logger.info("Request Created For Uploading Product Image {} :",  productId);
        String products= fileService.uploadFile(image,imageUploadPath2);
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
        InputStream resource= fileService.getResource(imageUploadPath2,product.getProductImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
        logger.info(" product Image Getting Successfully");
    }
}


