package com.accenture.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {
    /**
     * Creates a RestTemplate bean with load balancing enabled.
     * The RestTemplate bean can be used to make HTTP requests to other services.
     * @return RestTemplate bean
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
