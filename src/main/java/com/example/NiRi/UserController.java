package com.example.NiRi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {


    private List<User> registeredUsers = new ArrayList<>();

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }


        if (!user.getEmail().contains("@")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format");
        }


        if (registeredUsers.stream().anyMatch(u -> u.getName().equals(user.getName()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
        }


        registeredUsers.add(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
