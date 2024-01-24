package com.example.NiRi.controller;

import com.example.NiRi.modules.User;
import com.example.NiRi.repository.UserRepository;
import com.example.NiRi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PutMapping("/updateProfile/{id}")
    public User updateProfile(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        return userService.updateProfileInformation(id, name, email);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getByEmail")
    public User getUserByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
    @GetMapping("/getByRole")
    public List<User> getUsersByRole(@RequestParam String role) {
        return userRepository.findByRole(role);
    }
    @GetMapping("/getByUsername")
    public User getUserByUsername(@RequestParam String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
    @PutMapping("/resetPassword")
    public void resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        userService.updateUserPassword(email, newPassword);
    }
    @PostMapping("/login")
    public User loginUser(@RequestParam String email, @RequestParam String password) {
        return userService.authenticateUser(email, password);
    }
    @PutMapping("/updateRoles/{id}")
    public void updateRoles(@PathVariable Long id, @RequestBody List<String> roles) {
        userService.updateUserRoles(id, roles);
    }

    @PostMapping("/forgotPassword")
    public void forgotPassword(@RequestParam String email) {
        userService.generatePasswordResetToken(email);
    }

    @GetMapping("/getProfile/{id}")
    public User getProfile(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
