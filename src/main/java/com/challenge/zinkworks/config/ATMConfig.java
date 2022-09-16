package com.challenge.zinkworks.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ATM Configuration.
 */
@Configuration
public class ATMConfig {

    /**
     * Method to model an Entity into a DTO and vice versa.
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
