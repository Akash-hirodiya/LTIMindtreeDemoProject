package com.example.order_service.service;

import com.example.order_service.client.ProductClient;
import com.example.order_service.model.CartItem;
import com.example.order_service.model.Order;
import com.example.order_service.repository.CartRepository;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public Order checkout(Long userId) {

        List<CartItem> cartItems = cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = 0;

        for (CartItem item : cartItems) {

            Map<String, Object> product = productClient.getProductById(item.getProductId());

            Double price = Double.valueOf(product.get("price").toString());

            totalAmount += price * item.getQuantity();
        }

        Order order = Order.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .orderDate(LocalDateTime.now())
                .status("PLACED")
                .build();

        Order savedOrder = orderRepository.save(order);

        cartRepository.deleteByUserId(userId);

        return savedOrder;
    }
}
