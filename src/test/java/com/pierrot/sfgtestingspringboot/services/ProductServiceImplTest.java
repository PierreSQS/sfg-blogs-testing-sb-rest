package com.pierrot.sfgtestingspringboot.services;

import com.pierrot.sfgtestingspringboot.exception.ProductAlreadyExistsException;
import com.pierrot.sfgtestingspringboot.model.Product;
import com.pierrot.sfgtestingspringboot.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    List<Product> productList;

    @Mock
    ProductRepository productRepoMock;

    @InjectMocks
    ProductServiceImpl productServ;

    @BeforeEach
    void setUp() {
        productList = List.of(new Product(1,"Samsung Galaxy", 625.50f), new Product(2,"Desktop PC", 2099.99f));
    }

    @Test
    void addProduct() throws ProductAlreadyExistsException {
        // When
        Product productToAdd = productList.get(1);
        when(productRepoMock.existsById(anyInt())).thenReturn(false);
        when(productRepoMock.save(any())).thenReturn(productToAdd);

        // Then
        assertThat(productServ.addProduct(productToAdd)).isEqualTo(productToAdd);
        verify(productRepoMock).save(productToAdd);
    }

    @Test()
    void addProductAlreadyExists() {
        // When
        Product productToAdd = productList.get(1);
        when(productRepoMock.existsById(anyInt())).thenReturn(true);

        assertThrows(ProductAlreadyExistsException.class, () ->
                productServ.addProduct(productToAdd));

        verifyNoMoreInteractions(productRepoMock);

    }

    @Test
    void getAllProducts() {
        // When
        when(productRepoMock.findAll()).thenReturn(productList);

        // Then
        assertThat(productServ.getAllProducts()).hasSize(2).contains(productList.get(0));
    }

    @Test
    void getProductByid() {
        // When
        Product productMock = productList.get(0);
        when(productRepoMock.findById(anyInt())).thenReturn(Optional.of(productMock));

        assertThat(productServ.getProductByid(1)).isEqualTo(productMock);
    }

    @Test
    void deleteProductById() {
        // When
        Product productToDeleteMock = productList.get(0);
        when(productRepoMock.findById(anyInt())).thenReturn(Optional.of(productToDeleteMock));

        // Then
        assertThat(productServ.deleteProductById(1)).isEqualTo(productToDeleteMock);

        verify(productRepoMock).deleteById(anyInt());

    }
}