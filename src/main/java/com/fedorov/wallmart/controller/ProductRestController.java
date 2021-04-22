package com.fedorov.wallmart.controller;


import com.fedorov.wallmart.entity.Product;
import com.fedorov.wallmart.model.ProductModel;
import com.fedorov.wallmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> getProduct(@PathVariable("id") Long productModelId) {
        if (productModelId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ProductModel productModel = productService.getById(productModelId);
        if (productModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductModel>(productService.getById(productModelId), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Validated ProductModel productModel) {
        HttpHeaders headers = new HttpHeaders();
        if (productModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.create(productModel);
        return new ResponseEntity<>(productModel, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> updateProduct(@RequestBody @Validated ProductModel productModel, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (productModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long id = productModel.getId();
        productService.update(productModel, id);
        return new ResponseEntity<>(productModel, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> deleteProduct(@PathVariable("id") Long productModelId) {
        if (productService.getById(productModelId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(productModelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productModelList = productService.readAll();
        if (productModelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productModelList, HttpStatus.OK);
    }

}