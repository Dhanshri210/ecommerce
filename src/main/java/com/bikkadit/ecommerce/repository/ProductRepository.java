package com.bikkadit.ecommerce.repository;

import com.bikkadit.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    //Search
    List<Product> findByTitleContaining(String subTitle);

    List<Product> findByLiveTrue();


}
