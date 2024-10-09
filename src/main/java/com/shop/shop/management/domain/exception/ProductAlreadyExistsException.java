package com.shop.shop.management.domain.exception;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends BaseShopException {
    public ProductAlreadyExistsException(){
        this.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.setMessage("The product already exists");
    }
}
