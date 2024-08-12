package com.rishi.electronic.store.repositories;

import com.rishi.electronic.store.entites.Category;
import com.rishi.electronic.store.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

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

