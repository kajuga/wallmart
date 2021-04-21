package com.fedorov.wallmart.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "wallmart", name = "products")
@Getter
@Setter
@ToString
public class Product extends BaseEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private String category;

    @Column(name = "producer_id")
    private String producer;

    @Column(name = "price")
    private BigDecimal price;
}


