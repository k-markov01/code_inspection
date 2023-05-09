package com.tusofia.codeinspection.dto;

import com.tusofia.codeinspection.model.Authority;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String email;

    private String password;

    private Set<Authority> authority;
}
