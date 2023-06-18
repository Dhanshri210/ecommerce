package com.bikkadit.ecommerce.service;

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
   List<ProductDto> getAllProducts();

   //Get All Live
    List<ProductDto> getAllLive();

    //Search User
    List<ProductDto> searchByTitle(String subTitle);




}
