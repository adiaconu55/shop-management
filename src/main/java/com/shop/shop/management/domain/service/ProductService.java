package com.shop.shop.management.domain.service;

import com.shop.shop.management.api.dto.ProductSaveDto;

public interface ProductService {

    ProductSaveDto saveProduct(ProductSaveDto requestDto);
}
