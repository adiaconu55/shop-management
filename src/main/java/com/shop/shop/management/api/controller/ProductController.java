package com.shop.shop.management.api.controller;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;
import com.shop.shop.management.domain.choreographer.ProductChoreographer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductChoreographer productChoreographer;

    @Autowired
    public void ProductController(ProductChoreographer productChoreographer){
        this.productChoreographer = productChoreographer;
    }

    @PostMapping(value="/save-product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody @Valid ProductDto requestDto){
        return new ResponseEntity<>(productChoreographer.saveProduct(requestDto), HttpStatus.OK);
    }

    @PostMapping(value="/delete-product")
    public ResponseEntity<ProductDto> deleteProduct(@RequestBody String productName){
        return new ResponseEntity<>(productChoreographer.deleteProduct(productName), HttpStatus.OK);
    }

    @PostMapping(value="/change-price")
    public ResponseEntity<ProductDto> changePrice(@RequestBody @Valid ChangePriceRequestDto requestDto){
        return new ResponseEntity<>(productChoreographer.changePrice(requestDto), HttpStatus.OK);
    }

    @GetMapping("/get-product/{productName}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productName) {
        return new ResponseEntity<ProductDto>(productChoreographer.getProduct(productName), HttpStatus.OK);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<List<ProductDto>>(productChoreographer.getAllProducts(), HttpStatus.OK);
    }

}
