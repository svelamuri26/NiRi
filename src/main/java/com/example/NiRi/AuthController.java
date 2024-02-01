package com.example.NiRi;

import com.example.NiRi.modules.User;
import com.example.NiRi.repository.UserRepository;
import com.example.NiRi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil; // Inject JwtTokenUtil

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.authenticateUser(email, password);
            if (user != null) {

                String token = jwtTokenUtil.generateToken(user.getUsername());

                Map<String, String> response = new HashMap<>();
                response.put("token", token);

                return ResponseEntity.ok(response);
            }
        } catch (RuntimeException e) {

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password");
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
