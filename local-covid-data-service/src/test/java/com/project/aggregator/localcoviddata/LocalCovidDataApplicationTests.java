package com.project.aggregator.localcoviddata;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocalCovidDataApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;


    private final static String validCountry = "PL";

    @Test
    void contextLoads() {}

    @Disabled
    @Test
    void shouldReturnOKStatusGetYesterdayStatistics(){
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Connection", "keep-alive");
        //when
        final ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:8011/local-covid-data-service/results/yesterday?country={country}",
                HttpMethod.GET, new HttpEntity<>(null, null), Void.class, validCountry);
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
