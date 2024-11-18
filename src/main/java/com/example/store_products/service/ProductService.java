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

  public List<Product> getProductsByCategory(String category) {
    return productRepository.findByCategoria(category);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Long productId, Product updatedProduct) {
    return productRepository
      .findById(productId)
      .map(product -> {
        product.setNombre(updatedProduct.getNombre());
        product.setPrecio(updatedProduct.getPrecio());
        product.setValoracion(updatedProduct.getValoracion());
        product.setOferta(updatedProduct.getOferta());
        product.setPrecioOriginal(updatedProduct.getPrecioOriginal());
        product.setImg(updatedProduct.getImg());
        product.setEsencial(updatedProduct.getEsencial());
        product.setCategoria(updatedProduct.getCategoria());
        product.setStock(updatedProduct.getStock());
        return productRepository.save(product);
      })
      .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
  }

  public void deleteProduct(Long productId) {
    if (productRepository.existsById(productId)) {
      productRepository.deleteById(productId);
    } else {
      throw new RuntimeException("product not found with id: " + productId);
    }
  }
}
