package com.smartgreenhouse.itbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.smartgreenhouse")
@EnableJpaRepositories("com.smartgreenhouse.repositories")
@EntityScan("com.smartgreenhouse.entities") 
@EnableFeignClients
@EnableDiscoveryClient
public class ControleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleServiceApplication.class, args);
	}

}
