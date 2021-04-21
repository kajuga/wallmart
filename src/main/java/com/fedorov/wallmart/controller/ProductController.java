package com.fedorov.wallmart.controller;

import com.fedorov.wallmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



//    @GetMapping(value = "/clients/{id}")
//    public ResponseEntity&lt;Client&gt; read(@PathVariable(name = "id") int id) {
//        final Client client = clientService.read(id);
//
//        return client != null
//                ? new ResponseEntity&lt;&gt;(client, HttpStatus.OK)
//           : new ResponseEntity&lt;&gt;(HttpStatus.NOT_FOUND);
//    }





}
