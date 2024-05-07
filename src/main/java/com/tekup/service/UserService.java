package com.tekup.service;

import com.tekup.entity.User;
import com.tekup.model.UserDto;

import java.util.List;

public interface UserService {

    // Saves a user
    User save(UserDto user);

    // Retrieves all users
    List<User> findAll();

    // Retrieves a user by username
    User findOne(String username);

    User createEmployee(UserDto user);

    User createManager(UserDto user) throws Exception;
}