package com.citasmedicas.citasmedicas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.citasmedicas.citasmedicas")

public class MapperConfig {
        
    @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();

    }
}
