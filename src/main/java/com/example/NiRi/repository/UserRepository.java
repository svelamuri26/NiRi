package com.example.NiRi.repository;

import com.example.NiRi.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);
    Optional<User> findByUsername(String username);
    Optional<User> findByResetToken(String resetToken);
    List<User> findByName(String name);

    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password, u.role = :role WHERE u.id = :id")
    int updateUserDetailsById(Long id, String name, String email, String password, String role);

}
