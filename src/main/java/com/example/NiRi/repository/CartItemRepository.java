package com.example.NiRi.repository;

import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.Products;
import com.example.NiRi.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);
    //List<CartItem> findByCartId(int cartItemId);
    List<CartItem> findListByCartItemId(int cartItemId);
    CartItem findByCartItemId(int cartItemId);

    CartItem findByUserIdAndProductId(Long userId, Long productId);

    CartItem findByUserAndProduct(User user, Products product);

}
