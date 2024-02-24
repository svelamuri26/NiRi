package com.example.NiRi.controller;

import com.example.NiRi.modules.User;
import com.example.NiRi.repository.UserRepository;
import com.example.NiRi.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Test
    void registerUser() {
        User userToRegister = new User();
        when(userService.saveUser(userToRegister)).thenReturn(userToRegister);

        User registeredUser = userController.registerUser(userToRegister);

        assertEquals(userToRegister, registeredUser);
        verify(userService, times(1)).saveUser(userToRegister);
    }

    @Test
    void getAllUsers() {
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(expectedUsers);

        List<User> actualUsers = userController.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserByEmail() {
        String userEmail = "test@example.com";
        User expectedUser = new User();

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(expectedUser));

        User actualUser = userController.getUserByEmail(userEmail);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findByEmail(userEmail);
    }

    @Test
    void getUserById() {
        long userId = 1L;
        User expectedUser = new User();
        when(userService.getUserById(userId)).thenReturn(expectedUser);

        User actualUser = userController.getUserById(userId);

        assertEquals(expectedUser, actualUser);
        verify(userService, times(1)).getUserById(userId);
    }
}
