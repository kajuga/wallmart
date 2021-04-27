package com.fedorov.wallmart.service;

import com.fedorov.wallmart.model.CategoryModel;
import java.util.List;

public interface CategoryService {
    void create(CategoryModel categoryModel);
    List<CategoryModel> readAll();
    CategoryModel getById (Long id);
    boolean update(CategoryModel categoryModel, Long id);
    void delete(Long id);
}