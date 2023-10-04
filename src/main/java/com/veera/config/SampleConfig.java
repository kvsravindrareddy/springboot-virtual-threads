package com.veera.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SampleConfig {

    @Bean
    private String testString() {
        System.out.println(".....private");
        return "String";
    }
}
