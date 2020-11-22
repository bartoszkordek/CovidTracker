package com.project.aggregator.covid19trackingnarrativaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Covid19TrackingNarrativaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Covid19TrackingNarrativaApplication.class, args);
    }

}
