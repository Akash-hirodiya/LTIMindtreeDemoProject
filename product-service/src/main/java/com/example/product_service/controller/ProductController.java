package com.example.product_service.controller;

import com.example.product_service.model.Favorite;
import com.example.product_service.model.Product;
import com.example.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // ✅ GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ✅ Add new product (Admin only - role check inside JWT token)
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    // ✅ Favorite a product
    @PostMapping("/{id}/favorite")
    public ResponseEntity<Favorite> markFavorite(
            @PathVariable Long id,
            @RequestAttribute("userId") Long userId   // Extracted from JWT
    ) {
        return ResponseEntity.ok(productService.markFavorite(userId, id));
    }

    // ✅ Get favorites for logged-in user
    @GetMapping("/favorites")
    public ResponseEntity<List<Favorite>> favorites(
            @RequestAttribute("userId") Long userId
    ) {
        return ResponseEntity.ok(productService.getFavorites(userId));
    }
}
