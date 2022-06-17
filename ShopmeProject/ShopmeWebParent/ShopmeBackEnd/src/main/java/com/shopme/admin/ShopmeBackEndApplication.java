package com.shopme.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan({"com.shopme.common.entity","com.shopme.admin.user"})

public class ShopmeBackEndApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ShopmeBackEndApplication.class, args);
	}

}
