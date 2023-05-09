package com.tusofia.codeinspection.service.impl;

import com.tusofia.codeinspection.dto.UserDto;
import com.tusofia.codeinspection.model.User;
import com.tusofia.codeinspection.repository.UserRepository;
import com.tusofia.codeinspection.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
        return new org.springframework.security.core.userdetails.User(foundUser.getEmail(), foundUser.getPassword(), new ArrayList<>());
    }

    @Override
    public User register(UserDto userDto) {
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setAuthority(userDto.getAuthority());

        return userRepository.save(newUser);
    }
}
