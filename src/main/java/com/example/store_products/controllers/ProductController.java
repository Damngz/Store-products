package com.example.store_products.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store_products.models.ApiResponse;
import com.example.store_products.models.Product;
import com.example.store_products.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;
  
  @GetMapping
  public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
    return ResponseEntity.ok(new ApiResponse<>(200,"ok", productService.getAllProducts()));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long productId) {
    Optional<Product> product = productService.getProductById(productId);
    return product
      .map(value -> ResponseEntity.ok(new ApiResponse<>(200, "OK", value)))
      .orElseGet(() -> ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ApiResponse<>(404, "Product not found", null)));
}

  @PostMapping
  public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);
    ApiResponse<Product> response = new ApiResponse<>(201, "Product created successfully", createdProduct);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }


  @PutMapping("/{productId}")
  public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long productId, @RequestBody Product productDetails) {
    try {
      Optional<Product> updatedProduct = Optional.ofNullable(productService.updateProduct(productId, productDetails));
      return updatedProduct
        .map(value -> ResponseEntity.ok(new ApiResponse<>(200, "Product updated successfully", value)))
        .orElseGet(() -> ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse<>(404, "Product not found", null)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok(new ApiResponse<>(404, "Product not found", null));
    }
  }

  @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long productId) {
      try {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Product deleted successfully", null));
      } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "Product not found", null));
      }
    }
}
