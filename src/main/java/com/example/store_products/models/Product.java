package com.example.store_products.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long productId;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private double precio;

  @Column(nullable = false)
  private double valoracion;

  @Column(nullable = false)
  private String oferta;

  @Column(nullable = true)
  private Double precio_original;

  @Column(nullable = true)
  private String img;

  @Column(nullable = false)
  private String esencial;

  @Column(nullable = false)
  private String categoria;

  @Column(nullable = false)
  private int stock;

  public Product() {}

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public double getValoracion() {
    return valoracion;
  }

  public void setValoracion(double valoracion) {
    this.valoracion = valoracion;
  }

  public String getOferta() {
    return oferta;
  }

  public void setOferta(String oferta) {
    this.oferta = oferta;
  }

  public Double getPrecioOriginal() {
    return precio_original;
  }

  public void setPrecioOriginal(Double precio_original) {
    this.precio_original = precio_original;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getEsencial() {
    return esencial;
  }

  public void setEsencial(String esencial) {
    this.esencial = esencial;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
