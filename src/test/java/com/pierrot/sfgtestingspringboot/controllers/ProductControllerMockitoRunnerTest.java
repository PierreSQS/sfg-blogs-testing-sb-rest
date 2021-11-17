package com.pierrot.sfgtestingspringboot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pierrot.sfgtestingspringboot.model.Product;
import com.pierrot.sfgtestingspringboot.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerMockitoRunnerTest {

    Product productMock1;
    Product productMock2;

    @Mock
    ProductService productServMock;

    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        productMock1= new Product(1,"Samsung Galaxy", 625.50f);
        productMock2 = new Product(2,"Desktop PC", 2099.99f);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void addProduct() throws Exception {
        // When
        when(productServMock.addProduct(any())).thenReturn(productMock1);

        // Then
        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productMock1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is(equalTo(productMock1.getName()))))
                .andDo(print());
    }

    @Test
    void getAllProducts() throws Exception{
        // When
        when(productServMock.getAllProducts()).thenReturn(List.of(productMock1,productMock2));

        // Then
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is(equalTo(productMock1.getName()))))
                .andDo(print());

    }

    @Test
    void getProductById() throws Exception {
        // When
        when(productServMock.getProductByid(anyInt())).thenReturn(productMock2);

        // Then
        mockMvc.perform(get("/api/v1/product/{id}",2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(equalTo(productMock2.getName()))))
                .andDo(print());
    }

    @Test
    void deleteProductById() throws Exception {
        // When
        when(productServMock.deleteProductById(anyInt())).thenReturn(productMock2);

        // Then
//        mockMvc.perform(delete("/api/v1/product"))
        mockMvc.perform(delete("/api/v1/product/{id}", 2L))
                .andExpect(status().isOk())
                .andDo(print());
    }
}