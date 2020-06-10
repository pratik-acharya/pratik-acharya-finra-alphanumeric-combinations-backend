package com.pratik.apps.finraserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class FinraServerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinraServerAppApplication.class, args);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
