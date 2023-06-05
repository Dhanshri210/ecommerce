package com.bikkadit.service;

import com.bikkadit.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userdto);

    UserDto updateUser(UserDto userdto, String userId);

    void deleteUser(String userId);

    List<UserDto> getAllUser();

    UserDto getUser(String userId);

    UserDto getUserByEmail(String userEmail);

    List<UserDto> searchUser(String keyword);

}

