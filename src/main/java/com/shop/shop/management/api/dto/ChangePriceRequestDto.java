package com.shop.shop.management.api.dto;

public class ChangePriceRequestDto {

    private String productName;
    private Integer price;

    public ChangePriceRequestDto() {
    }

    public ChangePriceRequestDto(String productName, Integer price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
