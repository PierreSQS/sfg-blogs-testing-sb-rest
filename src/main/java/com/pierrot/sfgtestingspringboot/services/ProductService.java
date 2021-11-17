package com.pierrot.sfgtestingspringboot.services;

import com.pierrot.sfgtestingspringboot.exception.ProductAlreadyExistsException;
import com.pierrot.sfgtestingspringboot.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product) throws ProductAlreadyExistsException;
    List<Product> getAllProducts();
    Product getProductByid(int id);
    Product deleteProductById(int id);



}
