package com.example.NiRi.service;


import com.example.NiRi.modules.User;
import com.example.NiRi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
    public void updateUserPassword(String email, String newPassword) {
        User user = getUserByEmail(email);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void updateUserRoles(Long id, List<String> roles) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setRole(String.join(",", roles));
        userRepository.save(user);
    }

    public void generatePasswordResetToken(String email) {
        User user = getUserByEmail(email);
        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        userRepository.save(user);

        sendPasswordResetEmail(user.getEmail(), resetToken);
    }

    public void resetPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken)
                .orElseThrow(() -> new RuntimeException("Invalid or expired reset token"));

        if (isResetTokenExpired(user.getResetTokenCreationTime())) {
            throw new RuntimeException("Reset token has expired");
        }

        user.setPassword(newPassword);
        user.setResetToken(null);
        userRepository.save(user);
    }

    private boolean isResetTokenExpired(LocalDateTime creationTime) {
        LocalDateTime expirationTime = creationTime.plus(1, ChronoUnit.HOURS);
        return LocalDateTime.now().isAfter(expirationTime);
    }

    private void sendPasswordResetEmail(String email, String resetToken) {
        String resetLink = "http://localhost:8080/NiRi/reset-password?token=" + resetToken;

        UserService emailService = null;
        emailService.sendPasswordResetEmail(email, resetLink);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateProfileInformation(Long id, String name, String email) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));


        user.setName(name);
        user.setEmail(email);

        return userRepository.save(user);
    }
}
