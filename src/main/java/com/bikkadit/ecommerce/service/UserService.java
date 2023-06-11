package com.bikkadit.ecommerce.service;

import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.UserDto;

import java.util.List;
import java.util.stream.Stream;

public interface UserService {

    UserDto createUser(UserDto userdto);

    UserDto updateUser(UserDto userdto, String userId);

    void deleteUser(String userId);

    PageableResponse<UserDto> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    UserDto getUser(String userId);

    UserDto getUserByEmail(String userEmail);

    Stream<UserDto> searchUser(String keyword);

}

