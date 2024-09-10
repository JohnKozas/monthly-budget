package com.portfolio.rest.restful.monthly_budget.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtosConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
