package com.example.order_service.controller;

import com.example.order_service.model.CartItem;
import com.example.order_service.model.Order;
import com.example.order_service.service.CartService;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;


    @PostMapping("/cart/add")
    public ResponseEntity<CartItem> addToCart(
            //@RequestAttribute("sub") Long userId,
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }
    //  Add to cart
//    @PostMapping("/cart/add")
//    public ResponseEntity<CartItem> addToCart(
//            @RequestAttribute("userId") Long userId,
//            @RequestParam Long productId,
//            @RequestParam Integer quantity
//    ) {
//        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
//    }


    @GetMapping("/cart")
    public ResponseEntity<List<CartItem>> getCart(
            //@RequestAttribute("userId") Long userId
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }


    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(
            //@RequestAttribute("userId") Long userId
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(orderService.checkout(userId));
    }
}
