package com.shop.shop.management;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;
import com.shop.shop.management.domain.entity.Price;
import com.shop.shop.management.domain.entity.Product;
import com.shop.shop.management.domain.exception.ProductAlreadyExistsException;
import com.shop.shop.management.domain.exception.ProductDoesNotExistException;
import com.shop.shop.management.domain.mapper.Mapper;
import com.shop.shop.management.domain.repository.ProductRepository;
import com.shop.shop.management.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDto productDto;
    private ChangePriceRequestDto changePriceRequestDto;
    private Product product;
    private Price price;

    @BeforeEach
    void setUp() {
        productDto = new ProductDto();
        productDto.setProductName("Test Product");
        productDto.setProductQty(10L);
        productDto.setPrice(100);

        changePriceRequestDto = new ChangePriceRequestDto();
        changePriceRequestDto.setProductName("Test Product");
        changePriceRequestDto.setPrice(150);

        product = new Product();
        product.setProductName("Test Product");
        product.setProductQty(10L);
        price = new Price();
        price.setPrice(100);
        price.setProduct(product);

        Set<Price> priceSet = new HashSet<>();
        priceSet.add(price);
        product.setPriceSet(priceSet);
    }


    @Test
    void saveProductShouldSaveProductWhenProductDoesNotExist() {
        //Given
        when(productRepository.findByProductName(productDto.getProductName())).thenReturn(Optional.empty());
        when(mapper.mapToProductEntity(productDto)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(mapper.mapToProductDto(product)).thenReturn(productDto);

        //When
        ProductDto savedProduct = productService.saveProduct(productDto);

        //Assert
        assertNotNull(savedProduct);
        assertEquals(productDto.getProductName(), savedProduct.getProductName());
        assertEquals(productDto.getProductQty(), savedProduct.getProductQty());
        assertEquals(productDto.getPrice(), savedProduct.getPrice());

        //Then
        verify(productRepository).findByProductName(productDto.getProductName());
        verify(productRepository).save(product);
    }

    @Test
    void saveProductShouldThrowProductAlreadyExistsExceptionWhenProductExists() {
        //Given
        when(productRepository.findByProductName(productDto.getProductName())).thenReturn(Optional.of(product));

        //When
        assertThrows(ProductAlreadyExistsException.class, () -> productService.saveProduct(productDto));

        //Then
        verify(productRepository).findByProductName(productDto.getProductName());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void changePriceShouldChangePriceWhenProductExists() {
        //Given
        when(productRepository.findByProductName(changePriceRequestDto.getProductName())).thenReturn(Optional.of(product));
        when(mapper.mapToProductDto(product)).thenReturn(productDto);

        //When
        ProductDto updatedProduct = productService.changePrice(changePriceRequestDto);

        //Assert
        assertNotNull(updatedProduct);
        assertEquals(changePriceRequestDto.getPrice(), updatedProduct.getPrice());

        //Then
        verify(productRepository).findByProductName(changePriceRequestDto.getProductName());
        verify(productRepository, never()).save(product); // No save call since we're using a transactional context
    }

    @Test
    void getProductShouldReturnTheLatestPrice() {
        //Given
        when(productRepository.findByProductName(changePriceRequestDto.getProductName())).thenReturn(Optional.of(product));
        when(mapper.mapToProductDto(product)).thenReturn(productDto);

        //When
        ProductDto updatedProduct = productService.changePrice(changePriceRequestDto);
        changePriceRequestDto.setPrice(200);
        updatedProduct = productService.changePrice(changePriceRequestDto);
        changePriceRequestDto.setPrice(300);
        updatedProduct = productService.changePrice(changePriceRequestDto);

        //Assert
        assertNotNull(updatedProduct);
        assertEquals(changePriceRequestDto.getPrice(),
                productService.getProduct(changePriceRequestDto.getProductName()).getPrice());
    }

    @Test
    void changePriceShouldThrowProductDoesNotExistExceptionWhenProductDoesNotExist() {
        //Given
        when(productRepository.findByProductName(changePriceRequestDto.getProductName())).thenReturn(Optional.empty());

        //When
        assertThrows(ProductDoesNotExistException.class, () -> productService.changePrice(changePriceRequestDto));

        //Then
        verify(productRepository).findByProductName(changePriceRequestDto.getProductName());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void getProductShouldReturnProductWhenProductExists() {
        //Given
        when(productRepository.findByProductName(productDto.getProductName())).thenReturn(Optional.of(product));
        when(mapper.mapToProductDto(product)).thenReturn(productDto);

        //When
        ProductDto foundProduct = productService.getProduct(productDto.getProductName());

        //Assert
        assertNotNull(foundProduct);
        assertEquals(productDto.getProductName(), foundProduct.getProductName());

        //Then
        verify(productRepository).findByProductName(productDto.getProductName());
    }

    @Test
    void getProductShouldThrowProductDoesNotExistExceptionWhenProductDoesNotExist() {
        //Given
        when(productRepository.findByProductName(productDto.getProductName())).thenReturn(Optional.empty());

        //When
        assertThrows(ProductDoesNotExistException.class, () -> productService.getProduct(productDto.getProductName()));

        //Then
        verify(productRepository).findByProductName(productDto.getProductName());
    }
}
