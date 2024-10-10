package com.shop.shop.management.domain.service;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;

public interface ProductService {

    ProductDto saveProduct(ProductDto requestDto);

    ProductDto changePrice(ChangePriceRequestDto request);

    ProductDto getProduct(String productName);
}
