package com.rishi.electronic.store.services;

import com.rishi.electronic.store.dtos.PageableResponse;
import com.rishi.electronic.store.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserServices {
    //create

    UserDto createUser(UserDto user);

    //update
    UserDto updateUser(UserDto userDto, String userId);

    //delete
    void deleteUser(String userId);

    //get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single user by id
    UserDto getUserById(String userId);
    //get single user by email
    UserDto getUserByEmail(String email);

    //search user
    List<UserDto> searchUser(String keyword);

    //other uer specific features

}
