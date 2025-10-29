package com.example.order_service.service;

import com.example.order_service.model.CartItem;
import com.example.order_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public CartItem addToCart(Long userId, Long productId, Integer quantity) {
        CartItem item = CartItem.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .build();

        return cartRepository.save(item);
    }

    public List<CartItem> getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
