package com.shop.shop.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.shop.shop.management")
public class ShopManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopManagementApplication.class, args);
	}
}
