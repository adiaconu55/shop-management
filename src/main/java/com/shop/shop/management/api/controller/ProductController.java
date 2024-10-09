package com.shop.shop.management.api.controller;

import com.shop.shop.management.api.dto.ProductSaveDto;
import com.shop.shop.management.domain.choreographer.ProductChoreographer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductChoreographer productChoreographer;

    @Autowired
    public void ProductController(ProductChoreographer productChoreographer){
        this.productChoreographer = productChoreographer;
    }

    @PostMapping(value="/save-product")
    public ResponseEntity<ProductSaveDto> saveProduct(@RequestBody ProductSaveDto requestDto){
        ProductSaveDto response = productChoreographer.saveProduct(requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return new ResponseEntity<>("Endpoint is reachable", HttpStatus.OK);
    }

}
