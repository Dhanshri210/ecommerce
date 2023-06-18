package com.bikkadit.ecommerce.repository;

import com.bikkadit.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    //Search
    Page<Product> findByTitleContaining(String subTitle,Pageable pageable);

    Page<Product> findByLiveTrue(Pageable pageable);


}
