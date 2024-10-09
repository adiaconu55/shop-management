package com.shop.shop.management.api.dto;

public class PriceDto {

    private int price;

    public PriceDto() {
    }

    public PriceDto(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
