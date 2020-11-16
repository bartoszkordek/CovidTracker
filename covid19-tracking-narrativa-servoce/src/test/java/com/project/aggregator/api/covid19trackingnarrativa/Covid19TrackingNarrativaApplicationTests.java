package com.project.aggregator.api.covid19trackingnarrativa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootApplication
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Covid19TrackingNarrativaApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads()  { assertNotNull(applicationContext);}

    private final static String validCountry = "poland";
    private final static String invalidCountry = "invalidcountry";
    private final static String validDate = "2020-11-12";
    private final static String futureDate = "2030-01-01";

    @Test
    void shouldReturnOKStatusGetCountryDate(){
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Connection", "keep-alive");
        // when
        final ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:8011/covid19-tracking-narrativa-service/results/stats?country={country}&date={date}",
                HttpMethod.GET, new HttpEntity<>(null, headers), Void.class, validCountry, validDate);
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
