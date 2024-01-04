package dev.bogdanbalalau.onlinestorebackend.controller;

import dev.bogdanbalalau.onlinestorebackend.dto.ProductDTO;
import dev.bogdanbalalau.onlinestorebackend.entity.Product;
import dev.bogdanbalalau.onlinestorebackend.service.ProductService;
import dev.bogdanbalalau.onlinestorebackend.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> productList = this.productService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("List with all saved products")
                .data(productList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setImage(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        product.setSize(productDTO.getSize());

        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Product created successfully")
                .data(this.productService.createProduct(product))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody Product product) {
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Product updated successfully")
                .data(this.productService.updateProduct(product))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id) {
        this.productService.deleteById(id);
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("Product deleted successfully")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
