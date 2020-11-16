package com.covid.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SearchMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchMicroserviceApplication.class, args);
	}

}
