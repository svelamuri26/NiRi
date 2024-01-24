package com.example.NiRi;

import com.example.NiRi.modules.User;
import com.example.NiRi.repository.UserRepository;
import com.example.NiRi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        User user1 = new User("John Doe", "john@example.com", "password123", "USER", "john.doe");
        User user2 = new User("Jane Smith", "jane@example.com", "password456", "ADMIN", "jane.smith");
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();
        assertEquals(userList, result);
    }


    @Test
    public void testSaveUser() {

        User userToSave = new User("Test User", "test@example.com", "testPassword", "testRole", "test.user");
        when(userRepository.save(any(User.class))).thenReturn(userToSave);

        User savedUser = userService.saveUser(userToSave);

        assertEquals(userToSave, savedUser);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testUpdateUserPassword() {

        User existingUser = new User("Test User", "test@example.com", "oldPassword", "testRole", "test.user");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(existingUser));

        userService.updateUserPassword("test@example.com", "newPassword");

        assertEquals("newPassword", existingUser.getPassword());
        verify(userRepository).save(existingUser);
    }


    @Test
    public void testUpdateUserRoles() {

        User existingUser = new User("Test User", "test@example.com", "oldPassword", "testRole", "test.olduser");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));
        userService.updateUserRoles(1L, Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        assertEquals("ROLE_USER,ROLE_ADMIN", existingUser.getRole());
        verify(userRepository).save(existingUser);
    }

    @Test
    public void testResetPasswordWithInvalidToken() {

        String invalidToken = "invalidToken";
        when(userRepository.findByResetToken(invalidToken)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.resetPassword(invalidToken, "newPassword"));
    }

}

