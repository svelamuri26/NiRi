package com.example.NiRi.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String username;
    private String resetToken;
    private LocalDateTime resetTokenCreationTime;

     public User(String name, String email, String password, String role, String username, String resetToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.resetToken = resetToken;
    }

    public User(String testUser, String mail, String testPassword, String testRole, String s) {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRolesList() {
        return Arrays.asList(role.split(","));
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenCreationTime() {
        return resetTokenCreationTime;
    }

    public void setResetTokenCreationTime(LocalDateTime resetTokenCreationTime) {
        this.resetTokenCreationTime = resetTokenCreationTime;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
