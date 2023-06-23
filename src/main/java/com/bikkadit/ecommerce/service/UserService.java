package com.bikkadit.ecommerce.service;

import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.UserDto;
import java.util.stream.Stream;

public interface UserService {

    //Create User
    UserDto createUser(UserDto userdto);

    //Update User
    UserDto updateUser(UserDto userdto, String userId);

    //Delete User
    void deleteUser(String userId);

    //Get All User
    PageableResponse<UserDto> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Get Single Users
    UserDto getUser(String userId);

    //Get USer By Email
    UserDto getUserByEmail(String userEmail);

    //Search User
    Stream<UserDto> searchUser(String keyword);

}

