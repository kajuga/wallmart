package com.fedorov.wallmart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @ApiModelProperty(notes = "The database generated product ID")
    private Long id;
    @ApiModelProperty(notes = "The name of the product")
    private String name;
    @ApiModelProperty(notes = "The product description")
    private String description;
    @ApiModelProperty(notes = "The category ID of the product")
    private Long categoryId;
    @ApiModelProperty(notes = "The producer ID of the product")
    private Long producerId;
    @ApiModelProperty(notes = "The price of the product", required = true)
    private BigDecimal price;
}
