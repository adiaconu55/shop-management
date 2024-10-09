package com.shop.shop.management.domain.service.impl;

import com.shop.shop.management.api.dto.ProductSaveDto;
import com.shop.shop.management.domain.entity.Price;
import com.shop.shop.management.domain.entity.Product;
import com.shop.shop.management.domain.exception.ProductAlreadyExistsException;
import com.shop.shop.management.domain.repository.ProductRepository;
import com.shop.shop.management.domain.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LogManager.getLogger(ProductService.class);

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductSaveDto saveProduct(ProductSaveDto requestDto){
        log.info("Saving product with name {}",requestDto.getProductName());

        productRepository.findByProductName(requestDto.getProductName())
                .ifPresent(course -> {throw new ProductAlreadyExistsException();});

        var newProduct = modelMapper.map(requestDto, Product.class);

        var price = modelMapper.map(requestDto.getPrice(), Price.class);
        newProduct.addPrice(price);

        var saveProduct = productRepository.save(newProduct);

        return modelMapper.map(saveProduct, ProductSaveDto.class);
    }
}
