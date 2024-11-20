package com.rishi.electronic.store.services;

import com.rishi.electronic.store.dto.CategoryDto;
import com.rishi.electronic.store.dto.PageableResponse;

public interface CategoryService {
    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete
    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize, String sortBy,String sortDir);

    //get single category detail
    CategoryDto get(String categoryId);

    //search:
}
