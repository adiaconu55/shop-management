package com.shop.shop.management.domain.choreographer;

import com.shop.shop.management.api.dto.ChangePriceRequestDto;
import com.shop.shop.management.api.dto.ProductDto;
import com.shop.shop.management.domain.constants.SecurityRoleEnum;
import com.shop.shop.management.domain.exception.CustomErrorResponseException;
import com.shop.shop.management.domain.exception.ProductAlreadyExistsException;
import com.shop.shop.management.domain.exception.ProductDoesNotExistException;
import com.shop.shop.management.domain.service.ProductService;
import com.shop.shop.management.security.CustomUserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class ProductChoreographer {

    private static final Logger log = LogManager.getLogger(ProductChoreographer.class);

    private ProductService productService;

    @Autowired
    public ProductChoreographer(ProductService productService) {
        this.productService = productService;
    }

    public ProductDto saveProduct(ProductDto requestDto){
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(MessageFormat.format("User {0} with {1} role",user.getUsername(),
                SecurityRoleEnum.valueOf(user.getRole())));
        ProductDto response = null;
        try{
            response = productService.saveProduct(requestDto);
        } catch (ProductAlreadyExistsException e){
            throw new CustomErrorResponseException(e.getMessage());
        } catch (Exception e){
            log.error(MessageFormat.format("Error saving product {0} with quantity {1} and price {2}",
                    requestDto.getProductName(),
                    requestDto.getProductQty(),
                    requestDto.getPrice()),
                    e);
            throw new CustomErrorResponseException("An error occured in the application. Please contact the system administrator");
        }

        return response;
    }

    public ProductDto changePrice(ChangePriceRequestDto requestDto){
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(MessageFormat.format("User {0} with {1} role",user.getUsername(),
                SecurityRoleEnum.valueOf(user.getRole())));
        ProductDto response = null;
        try{
            response = productService.changePrice(requestDto);
        } catch (ProductDoesNotExistException e){
            throw new CustomErrorResponseException(e.getMessage());
        } catch (Exception e){
            log.error(MessageFormat.format("Error changing price of {0} with price {1}", requestDto.getProductName(),
                    requestDto.getPrice()), e);
            throw new CustomErrorResponseException("An error occured in the application. Please contact the system administrator");
        }

        return response;
    }

    public ProductDto getProduct(String productName){
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(MessageFormat.format("User {0} with {1} role",user.getUsername(),
                SecurityRoleEnum.valueOf(user.getRole())));
        ProductDto response = null;
        try{
            response = productService.getProduct(productName);
        } catch (ProductDoesNotExistException e){
            log.error(MessageFormat.format("Error getting product: {0}", productName));
            throw new CustomErrorResponseException(e.getMessage());
        } catch (Exception e){
            log.error(MessageFormat.format("Error getting product: {0}", productName),e);
            throw new CustomErrorResponseException("An error occured in the application. Please contact the system administrator");
        }

        return response;
    }

}
