package com.fedorov.wallmart.service.serviceImpl;

import com.fedorov.wallmart.model.ProductModel;
import com.fedorov.wallmart.entity.Product;
import com.fedorov.wallmart.repository.CategoryRepository;
import com.fedorov.wallmart.repository.ProductRepository;
import com.fedorov.wallmart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(ProductModel productModel) {
        log.info("In ProductServiceImpl create(ProductModel product)", productModel);
        Product productToSave = new Product();
        productToSave.setName(productModel.getName());
        productToSave.setDescription(productModel.getDescription());
        productToSave.setCategory(productModel.getCategoryId());
        productToSave.setPrice(productModel.getPrice());
        productRepository.save(productModelToProductTransformer(productModel));
    }

    @Override
    public ProductModel getById(Long id) {
        log.info("In ProductServiceImpl getById(Long id)", id);
        Product product =  productRepository.getOne(id);
        return productToModelTransformer(product);
    }

    @Override
    public List<ProductModel> readAll() {
        log.info("In ProductServiceImpl readAll()");
        List<ProductModel> productModels = new ArrayList<>();
        for (Product product: productRepository.findAll()) {
            productModels.add(productToModelTransformer(product));
        }
        return productModels;
    }

    @Override
    public boolean update(ProductModel productModel, Long id) {
        log.info("In ProductServiceImpl update(ProductModel productModel, Long id) " + id);
        Product updatedProduct = productRepository.getOne(id);
        updatedProduct.setId(id);
        updatedProduct.setName(productModel.getName());
        //TODO REPO для каждой составляющей!!
        updatedProduct.setCategory(categoryRepository.getOne(productModel.getCategoryId()));
        updatedProduct.setDescription(productModel.getDescription());
        updatedProduct.setProducer(productModel.getProducer());
        updatedProduct.setPrice(productModel.getPrice());
        productRepository.save(updatedProduct);
        return true;
    }

    @Override
    public void delete(Long id) {
        log.info("In ProductServiceImpl delete by Id{}", id);
        productRepository.deleteById(id);
    }


    // Product to ProductModel converter
    private ProductModel productToModelTransformer (Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setCategoryId(product.getCategory());
        productModel.setDescription(product.getDescription());
        productModel.setProducer(product.getProducer());
        productModel.setPrice(product.getPrice());
        return productModel;
    }

    // ProductModel to Product converter
    private Product productModelToProductTransformer (ProductModel productModel) {
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setCategory(productModel.getCategoryId());
        product.setDescription(productModel.getDescription());
        product.setProducer(productModel.getProducer());
        product.setPrice(productModel.getPrice());
        return product;
    }


}











