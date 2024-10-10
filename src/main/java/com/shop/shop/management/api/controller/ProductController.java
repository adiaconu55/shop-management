package com.shop.shop.management.api.controller;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;
import com.shop.shop.management.domain.choreographer.ProductChoreographer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductChoreographer productChoreographer;

    @Autowired
    public void ProductController(ProductChoreographer productChoreographer){
        this.productChoreographer = productChoreographer;
    }

    @PostMapping(value="/save-product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto requestDto){
        return new ResponseEntity<>(productChoreographer.saveProduct(requestDto), HttpStatus.OK);
    }

    @PostMapping(value="/change-price")
    public ResponseEntity<ProductDto> changePrice(@RequestBody ChangePriceRequestDto requestDto){
        return new ResponseEntity<>(productChoreographer.changePrice(requestDto), HttpStatus.OK);
    }

    @GetMapping("/get-product/{productName}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productName) {
        return new ResponseEntity<ProductDto>(productChoreographer.getProduct(productName), HttpStatus.OK);
    }

}
