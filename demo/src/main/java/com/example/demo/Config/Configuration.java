package com.example.demo.Config;

import feign.codec.ErrorDecoder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
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
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

}

