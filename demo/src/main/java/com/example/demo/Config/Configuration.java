package com.example.demo.Config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration
{
    @Bean
    public ErrorDecoder errorDecoder()
    {
        return new ClientErrorDecoder();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}

