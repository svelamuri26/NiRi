package com.example.NiRi.Repository;

import com.example.NiRi.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password WHERE u.id = :id")
    int updateUserDetailsById(Long id, String name, String email, String password);
}