package com.example.NiRi;

import com.example.NiRi.PasswordJWT;
import com.example.NiRi.modules.User;
import com.example.NiRi.repository.UserRepository;
import com.example.NiRi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordJWT passwordJWT;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository, PasswordJWT passwordJWT) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordJWT = passwordJWT;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        String hashedPassword = passwordJWT.encodePassword(user.getPassword());
        user.setPassword(hashedPassword);
        userService.saveUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.authenticateUser(email, password);
        if (user != null) {
            String token = passwordJWT.generateToken(user);
            return token;
        }
        throw new RuntimeException("Invalid email/password");
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestParam String email) {
        userService.generatePasswordResetToken(email);
        return "Password reset instructions sent to email";
    }

    @PutMapping("/resetPassword")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        userService.updateUserPassword(email, newPassword);
        return "Password successfully reset";
    }
}
