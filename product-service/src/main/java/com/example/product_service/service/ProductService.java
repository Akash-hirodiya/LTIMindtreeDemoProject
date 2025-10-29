package com.example.product_service.service;

import com.example.product_service.model.Favorite;
import com.example.product_service.model.Product;
import com.example.product_service.repository.FavoriteRepository;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;

    // ✅ Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ✅ Add new product (Admin only logic handled in controller)
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // ✅ Mark product as favorite
    public Favorite markFavorite(Long userId, Long productId) {
        Favorite favorite = Favorite.builder()
                .userId(userId)
                .productId(productId)
                .build();
        return favoriteRepository.save(favorite);
    }

    // ✅ Get user's favorite products
    public List<Favorite> getFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

}
