package com.fedorov.wallmart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long producerId;
    private BigDecimal price;
}
