package com.example.store_products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store_products.models.Product;
import com.example.store_products.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Long productId) {
    return productRepository.findById(productId);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Long productId, Product updatedProduct) {
    return productRepository
      .findById(productId)
      .map(product -> {
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        product.setCategory(updatedProduct.getCategory());
        return productRepository.save(product);
      })
      .orElseThrow(() -> new RuntimeException("Producto not found with id: " + productId));
  }

  public void deleteProduct(Long productId) {
    if (productRepository.existsById(productId)) {
      productRepository.deleteById(productId);
    } else {
      throw new RuntimeException("user not found with id: " + productId);
    }
  }
}
