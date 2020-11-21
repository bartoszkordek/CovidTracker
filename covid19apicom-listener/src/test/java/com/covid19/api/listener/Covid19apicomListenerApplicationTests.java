package com.covid19.api.listener;

import com.covid19.api.listener.model.CovidCountryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class Covid19apicomListenerApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment environment;

	ObjectMapper mapper;

	@BeforeEach
	void setUp() {
		mapper=new ObjectMapper();
	}

	@Test
	void apiShouldReturn200OkStatus(){

		String url=environment.getProperty("microservice.listen.api") + "/dayone/country/germany";

		ResponseEntity<CovidCountryResponse[]> response=restTemplate
				.getForEntity(url,CovidCountryResponse[].class);

		CovidCountryResponse[] countries=mapper
				.convertValue(response.getBody(),CovidCountryResponse[].class);

		CovidCountryResponse returnValue=countries[0];

		System.out.println("Status : "+response.getStatusCode());
		System.out.println(returnValue);

	}

}
