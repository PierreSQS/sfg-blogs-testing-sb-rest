package com.pierrot.sfgtestingspringboot.controllers;

import com.pierrot.sfgtestingspringboot.exception.ProductAlreadyExistsException;
import com.pierrot.sfgtestingspringboot.model.Product;
import com.pierrot.sfgtestingspringboot.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductAlreadyExistsException {
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @GetMapping("products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductByid(id);
    }

    @DeleteMapping("product/{id}")
    public Product deleteProductById(@PathVariable int id) {
        return productService.deleteProductById(id);
    }
}
