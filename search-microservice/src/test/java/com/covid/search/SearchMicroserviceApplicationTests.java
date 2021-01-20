package com.covid.search;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchMicroserviceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;


	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads()  { assertNotNull(applicationContext);}

	@Test
	void shouldReturnForbiddenStatus(){
		//given
		String sampleSearchUrl = "http://localhost:8011/search/POL?date=2021-01-10";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Connection", "keep-alive");
		// when
		final ResponseEntity<Void> response = restTemplate.exchange(
				sampleSearchUrl, HttpMethod.GET, new HttpEntity<>(null, headers), Void.class);
		// then
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	void shouldReturnOkStatusWhenAuthorized() {
		//given
		//create sample user
		String createSampleUserUrl = "http://localhost:8011/account/users";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Connection", "keep-alive");
		headers.setContentType(MediaType.APPLICATION_JSON);

		//create body
		final String testEmail = new Random().nextInt() + "@wp.pl";
		final String testPassword = "test12314";
		Map<String, String> createSampleUserBody = new HashMap<>();
		createSampleUserBody.put("firstName", "Test");
		createSampleUserBody.put("lastName", "User");
		createSampleUserBody.put("password", testPassword);
		createSampleUserBody.put("email", testEmail);

		String createSampleUserBodyJsonMap = new Gson().toJson(createSampleUserBody);

		//add JSON content type header
		headers.add("Content-Type", "application/json");

		final ResponseEntity<String> createSampleUserResponse = restTemplate.exchange(
				createSampleUserUrl, HttpMethod.POST, new HttpEntity<>(createSampleUserBodyJsonMap, headers), String.class);
		assertEquals(HttpStatus.CREATED, createSampleUserResponse.getStatusCode());


		//login sample user
		String loginSampleUserUrl = "http://localhost:8011/account/login";
		//create body
		Map<String, String> loginSampleUserBody = new HashMap<>();
		loginSampleUserBody.put("email", testEmail);
		loginSampleUserBody.put("password", testPassword);
		String loginSampleUserBodyJsonMap = new Gson().toJson(loginSampleUserBody);

		final ResponseEntity<Void> loginSampleUserResponse = restTemplate.exchange(
				loginSampleUserUrl, HttpMethod.POST, new HttpEntity<>(loginSampleUserBodyJsonMap, new HttpHeaders()), Void.class);
		assertEquals(HttpStatus.OK, loginSampleUserResponse.getStatusCode());

		// when
		String sampleSearchUrl = "http://localhost:8011/search/POL?date=2021-01-10";
		String tokenHeaderName = "token";
		String tokenValue = loginSampleUserResponse.getHeaders().getFirst(tokenHeaderName);
		headers.add("Authorization", tokenValue);
		final ResponseEntity<Void> response = restTemplate.exchange(
				sampleSearchUrl, HttpMethod.GET, new HttpEntity<>(null, headers), Void.class);
		// then
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
