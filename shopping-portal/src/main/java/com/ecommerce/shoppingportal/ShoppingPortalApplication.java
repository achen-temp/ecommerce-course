package com.ecommerce.shoppingportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingPortalApplication.class, args);
	}

}
