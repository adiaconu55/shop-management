package com.shop.shop.management.api.dto;

import com.shop.shop.management.domain.entity.Price;

public class ProductSaveDto {

    private String productName;
    private Long productQty;
    private Integer price;

    public ProductSaveDto() {
    }

    public ProductSaveDto(String productName, Long productQty, Integer price) {
        this.productName = productName;
        this.productQty = productQty;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductQty() {
        return productQty;
    }

    public void setProductQty(Long productQty) {
        this.productQty = productQty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
