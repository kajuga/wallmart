package com.fedorov.wallmart.service.serviceImpl;

import com.fedorov.wallmart.model.ProductModel;
import com.fedorov.wallmart.entity.Product;
import com.fedorov.wallmart.repository.CategoryRepository;
import com.fedorov.wallmart.repository.ProducerRepository;
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
    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public void create(ProductModel productModel) {
        log.info("In ProductServiceImpl create(ProductModel product)", productModel);
        Product productToSave = new Product();
        productToSave.setName(productModel.getName());
        productToSave.setDescription(productModel.getDescription());
        productToSave.setCategory(categoryRepository.getOne(productModel.getCategoryId()));
        productToSave.setProducer(producerRepository.getOne(productModel.getProducerId()));
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
        updatedProduct.setDescription(productModel.getDescription());
        updatedProduct.setCategory(categoryRepository.getOne(productModel.getCategoryId()));
        updatedProduct.setProducer(producerRepository.getOne(productModel.getProducerId()));
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
        productModel.setCategoryId(product.getCategory().getId());
        productModel.setDescription(product.getDescription());
        productModel.setProducerId(product.getProducer().getId());
        productModel.setPrice(product.getPrice());
        return productModel;
    }

    // ProductModel to Product converter
    private Product productModelToProductTransformer (ProductModel productModel) {
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setCategory(categoryRepository.getOne(productModel.getCategoryId()));
        product.setProducer(producerRepository.getOne(productModel.getProducerId()));
        product.setPrice(productModel.getPrice());
        return product;
    }


}











