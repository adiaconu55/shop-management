package com.shop.shop.management.domain.exception;

import org.springframework.http.HttpStatus;

public class ProductDoesNotExistException extends BaseShopException {
    public ProductDoesNotExistException(){
        this.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.setMessage("The product does not exist");
    }
}
