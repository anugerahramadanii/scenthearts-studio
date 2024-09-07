package com.scentheartsstudio.scentheartsstudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScentheartsStudioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScentheartsStudioApplication.class, args);
	}
}
