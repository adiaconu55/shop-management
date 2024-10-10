package com.shop.shop.management.domain.choreographer;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;
import com.shop.shop.management.domain.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductChoreographer {

    private static final Logger log = LogManager.getLogger(ProductChoreographer.class);

    private ProductService productService;

    @Autowired
    public ProductChoreographer(ProductService productService) {
        this.productService = productService;
    }

    public ProductDto saveProduct(ProductDto requestDto){
        return productService.saveProduct(requestDto);
    }

    public ProductDto changePrice(ChangePriceRequestDto requestDto){
        return productService.changePrice(requestDto);
    }

    public ProductDto getProduct(String productName){
        return productService.getProduct(productName);
    }

}
