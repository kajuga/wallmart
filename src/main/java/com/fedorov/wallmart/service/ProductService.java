package com.fedorov.wallmart.service;

import com.fedorov.wallmart.entity.Product;
import com.fedorov.wallmart.model.ProductModel;

import java.util.List;

public interface ProductService {

    void create(ProductModel product);

    List<?> readAll();

    ProductModel getById (Long id);

    boolean update(ProductModel product, Long id);

    void delete(Long id);

}
