/*package com.example.NiRi.service;

import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.Products;
import com.example.NiRi.modules.User;
import com.example.NiRi.repository.CartItemRepository;
import com.example.NiRi.repository.ProductRepository;
import com.example.NiRi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartItemServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartItemService cartItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCartItemsByUserId() {

        Long userId = 1L;
        CartItem cartItem1 = new CartItem();
        CartItem cartItem2 = new CartItem();
        List<CartItem> cartItems = Arrays.asList(cartItem1, cartItem2);

        when(cartItemRepository.findByUserId(userId)).thenReturn(cartItems);

        List<CartItem> result = cartItemService.getCartItemsByUserId(userId);

        assertEquals(2, result.size());

    }

    @Test
    void addToCart() {

        Long userId = 1L;
        Long productId = 1L;
        int quantity = 2;

        User user = new User();
        Products product = new Products();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(cartItemRepository.findByUserIdAndProductId(userId, productId)).thenReturn(null);

        cartItemService.addToCart(userId, productId, quantity);

        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, times(1)).findById(productId);
        verify(cartItemRepository, times(1)).findByUserIdAndProductId(userId, productId);
        verify(cartItemRepository, times(1)).save(any());

    }

    @Test
    void removeFromCart() {

        Long cartItemId = 1L;
        CartItem cartItem = new CartItem();

        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));

        cartItemService.removeFromCart(cartItemId);

        verify(cartItemRepository, times(1)).findById(cartItemId);
        verify(cartItemRepository, times(1)).delete(cartItem);

    }
}*/
