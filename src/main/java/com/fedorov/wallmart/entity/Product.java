package com.fedorov.wallmart.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products", schema = "wallmart")
@Getter
@Setter
@ToString
public class Product extends BaseEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Column(name = "price")
    private BigDecimal price;
}

