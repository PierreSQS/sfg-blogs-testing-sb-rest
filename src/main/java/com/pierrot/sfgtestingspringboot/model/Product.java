package com.pierrot.sfgtestingspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Data
public class Product {

    @Id
    private int id;
    private String name;
    private float price;

    protected Product() {}

}
