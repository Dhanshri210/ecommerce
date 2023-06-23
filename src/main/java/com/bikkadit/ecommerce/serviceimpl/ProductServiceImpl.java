package com.bikkadit.ecommerce.serviceimpl;

import com.bikkadit.ecommerce.constant.AppConstant;
import com.bikkadit.ecommerce.entity.Category;
import com.bikkadit.ecommerce.entity.Product;
import com.bikkadit.ecommerce.exception.ResourceNotFoundException;
import com.bikkadit.ecommerce.helper.Helpers;
import com.bikkadit.ecommerce.payload.CategoryDto;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.ProductDto;
import com.bikkadit.ecommerce.repository.ProductRepository;
import com.bikkadit.ecommerce.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${product.profile.image.path}")
    private String imageUploadPath2;

    private static  Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    //Create Product
    @Override
    public ProductDto create(ProductDto productDto) {
        logger.info("Request Created For Create products");
        String productId = UUID.randomUUID().toString();
        productDto.setProductId(productId);
       Product product= modelMapper.map(productDto, Product.class);
       Product saveProduct=productRepository.save(product);
        logger.info("Request Completed For Create products");
       return modelMapper.map(saveProduct,ProductDto.class);
    }

    //Update Product Details
    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {
        logger.info("Request Created For Update products Details :{}",productId);
      Product product=  productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException(AppConstant.NOT_FOUNDs));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setSize(productDto.getSize());
        product.setQuantity(productDto.getQuantity());
        product.setDiscountPrice(product.getDiscountPrice());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductColour(productDto.getProductColour());
        product.setProductImage(productDto.getProductImage());
        Product update=productRepository.save(product);
        logger.info("Request Completed For Update products Details :{}",productId);
      return modelMapper.map(update,ProductDto.class);
    }

    //Delete Product
    @Override
    public void deleteProduct(String productId) {
        logger.info("Request Created For Delete products Details :{}", productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.DELETE_PRODUCT));
        String fullpath = imageUploadPath2 +product.getProductImage();
        try {
            Path path = Paths.get(fullpath);
                Files.delete(path);
                logger.info("Image Product Deleted :{}", productId);
            } catch (IOException e) {
            logger.info("Exception with Image Product Delete:{}",productId);
        }
            productRepository.delete(product);
            logger.info("Request Completed For Delete products Details :{}", productId);
        }
    // Get Single Product
    @Override
    public ProductDto getSingleProduct(String productId) {
        logger.info("Request Created For Get Single products Details :{}",productId);
        Product product=productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException(AppConstant.SINGLE_PRODUCT));
        logger.info("Request Completed For Delete products Details :{}",productId);
        return modelMapper.map(product,ProductDto.class);
    }

    //Get All Products
    @Override
    public PageableResponse<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        logger.info("Request Created For Get All products Details");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ?
                (Sort.by(sortBy).descending())
                : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);
        logger.info("Request Completed For Update products Details");
        return Helpers.getPageableResponse(page, ProductDto.class);
    }

    //Get All Products Are Live
    @Override
    public PageableResponse<ProductDto> getAllLive(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        logger.info("Request Created For All products Details Are live");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ?
                (Sort.by(sortBy).descending())
                : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        logger.info("Request Completed And All products Details Are Live");
        return Helpers.getPageableResponse(page, ProductDto.class);
    }

    //Search All Products By Its Title
    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle,Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        logger.info("Request Created For Search products Details");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ?
                (Sort.by(sortBy).descending())
                : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle,pageable);
        logger.info("Request Completed For Search products Details");
        return Helpers.getPageableResponse(page, ProductDto.class);
    }
}
