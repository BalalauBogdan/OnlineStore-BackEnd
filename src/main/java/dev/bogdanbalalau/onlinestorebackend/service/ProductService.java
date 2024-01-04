package dev.bogdanbalalau.onlinestorebackend.service;

import dev.bogdanbalalau.onlinestorebackend.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Integer id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Product product);
    void deleteById(Integer id);
}
