package com.fedorov.wallmart.service.serviceImpl;

import com.fedorov.wallmart.entity.Category;
import com.fedorov.wallmart.entity.Product;
import com.fedorov.wallmart.model.CategoryModel;
import com.fedorov.wallmart.model.ProductModel;
import com.fedorov.wallmart.repository.CategoryRepository;
import com.fedorov.wallmart.repository.ProducerRepository;
import com.fedorov.wallmart.repository.ProductRepository;
import com.fedorov.wallmart.service.CategoryService;
import com.fedorov.wallmart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(CategoryModel categoryModel) {
        log.info("In CategoryServiceImpl create(CategoryModel categoryModel)", categoryModel);
        Category categoryToSave = new Category();
        categoryToSave.setName(categoryModel.getName());
        categoryRepository.save(categoryToSave);
    }

    @Override
    public CategoryModel getById(Long id) {
        log.info("In CategoryServiceImpl getById(Long id)", id);
        //TODO проверки
        return categoryToModelTransformer(categoryRepository.getOne(id));
    }

    @Override
    public List<CategoryModel> readAll() {
        log.info("In CategoryServiceImpl readAll()");
        List<CategoryModel> categoryModels = new ArrayList<>();
        for (Category category: categoryRepository.findAll()) {
            categoryModels.add(categoryToModelTransformer(category));
        }
        return categoryModels;
    }

    @Override
    public boolean update(CategoryModel categoryModel, Long id) {
        log.info("In CategoryServiceImpl update(CategoryModel categoryModel, Long id) " + id);
        Category updatedCategory = categoryRepository.getOne(id);
        //TODO
        updatedCategory.setName(categoryModel.getName());
        categoryRepository.save(updatedCategory);
        return true;
    }

    @Override
    public void delete(Long id) {
        log.info("In CategoryServiceImpl delete by Id{}", id);
        categoryRepository.deleteById(id);
    }


    // Category to CategoryModel converter
    private CategoryModel categoryToModelTransformer (Category category) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());
        return categoryModel;
    }

    // CategoryModel to Category entity converter
    private Category categoryModelToCategoryTransformer (CategoryModel categoryModel) {
        Category category = new Category();
        category.setId(categoryModel.getId());
        category.setName(categoryModel.getName());
        return category;
    }
}











