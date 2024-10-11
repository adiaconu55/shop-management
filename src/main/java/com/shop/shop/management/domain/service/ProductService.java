package com.shop.shop.management.domain.service;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto saveProduct(ProductDto requestDto);

    ProductDto deleteProduct(String productName);

    ProductDto changePrice(ChangePriceRequestDto request);

    ProductDto getProduct(String productName);

    List<ProductDto> getAllProducts();
}
