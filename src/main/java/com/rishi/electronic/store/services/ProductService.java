package com.rishi.electronic.store.services;

import com.rishi.electronic.store.dtos.PageableResponse;
import com.rishi.electronic.store.dtos.ProductDto;
import com.rishi.electronic.store.entites.Product;

import java.util.List;

public interface ProductService {
    //create
    ProductDto create(ProductDto productDto);
    //update
    ProductDto update(ProductDto productDto, String productId);

    //delete
    void delete(String productId);

    //get single
    ProductDto get(String productId);

    //get all
    PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);

    //get all : Live
    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);

    //search product
    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);


    //create product with category
    ProductDto createWithCategory(ProductDto productDto,String categoryId);

    ProductDto updateCategory(String productId, String categoryId);

    PageableResponse<ProductDto> getAllOfCategory(String categoryId,int pageNumber,int pageSize,String sortBy,String sortDir);



    //other methods


}
