package com.bikkadit.service;

import com.bikkadit.payload.UserDto;

import java.util.List;
import java.util.stream.Stream;

public interface UserService {

    UserDto createUser(UserDto userdto);

    UserDto updateUser(UserDto userdto, String userId);

    void deleteUser(String userId);

    List<UserDto> getAllUser();

    UserDto getUser(String userId);

    UserDto getUserByEmail(String userEmail);

    Stream<UserDto> searchUser(String keyword);

}

