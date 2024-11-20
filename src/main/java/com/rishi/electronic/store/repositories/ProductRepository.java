package com.rishi.electronic.store.repositories;

import com.rishi.electronic.store.entity.Category;
import com.rishi.electronic.store.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    //Data JPA
    //search
    Page<Product> findByTitleContaining(String subTitle, Pageable pageable );

    //https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html

    Page<Product> findByLiveTrue(Pageable pageable);

    Page<Product> findByCategory(Category category,Pageable pageable);


    //other methods
    //custom finder methods
    //query methods

}

