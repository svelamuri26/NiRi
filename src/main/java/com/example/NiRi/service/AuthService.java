package com.example.NiRi.service;

import com.example.NiRi.modules.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public User authenticateUser(String email, String password) {
        User user = userService.getUserByEmail(email);
        String passwordEncoder = null;
        if (passwordEncoder.matches(password)) {
            return user;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}