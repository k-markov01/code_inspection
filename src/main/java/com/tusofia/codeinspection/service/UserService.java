package com.tusofia.codeinspection.service;


import com.tusofia.codeinspection.dto.UserDto;
import com.tusofia.codeinspection.model.User;

public interface UserService {
    User register(UserDto userDto);
}
