package com.bikkadit.ecommerce.service;

import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.ProductDto;

import java.util.List;

public interface ProductService {

    //Create
   ProductDto create(ProductDto productDto);

   //Update
   ProductDto updateProduct(ProductDto productDto , String productId);

   //Delete
   void deleteProduct(String productId);

  // Get Single
   ProductDto getSingleProduct(String productId);

   //Get All
   PageableResponse<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

   //Get All Live
    PageableResponse<ProductDto> getAllLive(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Search User
    PageableResponse<ProductDto> searchByTitle(String subTitle,Integer pageNumber, Integer pageSize, String sortBy, String sortDir);




}
