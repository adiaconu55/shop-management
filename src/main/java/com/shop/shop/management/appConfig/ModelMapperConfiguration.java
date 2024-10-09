package com.shop.shop.management.appConfig;

import com.shop.shop.management.api.dto.ProductSaveDto;
import com.shop.shop.management.domain.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<ProductSaveDto, Product>() {
            @Override
            protected void configure() {
                skip(destination.getPriceSet());
            }
        });

        return modelMapper;
    }

}
