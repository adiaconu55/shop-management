package com.shop.shop.management.domain.mapper;

import com.shop.shop.management.api.dto.ProductSaveDto;
import com.shop.shop.management.domain.entity.Price;
import com.shop.shop.management.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Mapper {

    public Product mapToProductEntity(ProductSaveDto dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setProductQty(dto.getProductQty());

        Set<Price> priceSet = new HashSet<>();
        if (dto.getPrice() != null) {
            Price price = new Price();
            price.setPrice(dto.getPrice());
            price.setProduct(product);
            priceSet.add(price);
        }

        product.setPriceSet(priceSet);
        return product;
    }

    public ProductSaveDto mapToProductSaveDto(Product product) {
        ProductSaveDto dto = new ProductSaveDto();
        dto.setProductName(product.getProductName());
        dto.setProductQty(product.getProductQty());

        if (!product.getPriceSet().isEmpty()) {
            Price price = product.getPriceSet().iterator().next();
            dto.setPrice(price.getPrice());
        }

        return dto;
    }
}
