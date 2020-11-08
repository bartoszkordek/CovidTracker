package com.covid19.api.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Covid19apicomListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19apicomListenerApplication.class, args);
	}
}
