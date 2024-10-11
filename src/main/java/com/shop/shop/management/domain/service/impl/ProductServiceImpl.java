package com.shop.shop.management.domain.service.impl;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;
import com.shop.shop.management.domain.entity.Price;
import com.shop.shop.management.domain.entity.Product;
import com.shop.shop.management.domain.exception.ProductAlreadyExistsException;
import com.shop.shop.management.domain.exception.ProductDoesNotExistException;
import com.shop.shop.management.domain.mapper.Mapper;
import com.shop.shop.management.domain.repository.ProductRepository;
import com.shop.shop.management.domain.service.ProductService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LogManager.getLogger(ProductService.class);

    private ProductRepository productRepository;
    private Mapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto saveProduct(ProductDto requestDto) {
        log.info("Saving product with name {}", requestDto.getProductName());
        productRepository.findByProductName(requestDto.getProductName())
                .ifPresent(product -> {
                    throw new ProductAlreadyExistsException();
                });

        var newProduct = mapper.mapToProductEntity(requestDto);
        Price price = new Price();
        price.setPrice(requestDto.getPrice());
        price.setProduct(newProduct);
        Set<Price> priceSet = new HashSet<>();
        priceSet.add(price);

        newProduct.setPriceSet(priceSet);
        var savedProduct = productRepository.save(newProduct);

        return mapper.mapToProductDto(savedProduct);
    }

    @Transactional
    public ProductDto changePrice(ChangePriceRequestDto request){
        log.info("Changing price for product {}", request.getProductName());

        Product product = productRepository.findByProductName(request.getProductName())
                .orElseThrow(() -> {
                    throw new ProductDoesNotExistException();
                });

        Price price = new Price();
        price.setPrice(request.getPrice());
        price.setProduct(product);
        product.addPrice(price);

        ProductDto productDto = mapper.mapToProductDto(product);
        productDto.setPrice(request.getPrice());
        return productDto;
    }

    public ProductDto getProduct(String productName){
        log.info("Fetching product {}", productName);

        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() -> {
                    throw new ProductDoesNotExistException();
                });

        return mapper.mapToProductDto(product);
    }
}
