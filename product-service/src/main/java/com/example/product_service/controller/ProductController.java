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


    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }


    @PostMapping("/{id}/favorite")
    public ResponseEntity<Favorite> markFavorite(
            @PathVariable Long id,
            @RequestAttribute("userId") Long userId
    ) {
        return ResponseEntity.ok(productService.markFavorite(userId, id));
    }


    @GetMapping("/favorites")
    public ResponseEntity<List<Favorite>> favorites(
            @RequestAttribute("userId") Long userId
    ) {
        return ResponseEntity.ok(productService.getFavorites(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
