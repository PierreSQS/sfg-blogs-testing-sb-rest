package com.pierrot.sfgtestingspringboot.services;

import com.pierrot.sfgtestingspringboot.exception.ProductAlreadyExistsException;
import com.pierrot.sfgtestingspringboot.model.Product;
import com.pierrot.sfgtestingspringboot.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product addProduct(Product product) throws ProductAlreadyExistsException {
        if (productRepo.existsById(product.getId()))
            throw new ProductAlreadyExistsException();

        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductByid(int id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product deleteProductById(int id) {
        Optional<Product> foundProductInDBOpt = productRepo.findById(id);
        if (foundProductInDBOpt.isPresent()){
            productRepo.deleteById(id);
            return foundProductInDBOpt.get();
        } else {
            return null;
        }
    }
}
