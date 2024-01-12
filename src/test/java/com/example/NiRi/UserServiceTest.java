package com.example.NiRi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.NiRi.User;
import com.example.NiRi.UserRepository;
import com.example.NiRi.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {

        User user1 = new User("John Doe", "john@example.com", "password123");
        User user2 = new User("Jane Smith", "jane@example.com", "password456");
        List<User> userList = Arrays.asList(user1, user2);


        when(userRepository.findAll()).thenReturn(userList);


        List<User> result = userService.getAllUsers();


        assertEquals(userList, result);
    }


}
