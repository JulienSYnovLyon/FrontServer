package com.fakeleboncoin.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration

public class FrontApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder
    configure(SpringApplicationBuilder builder) {
        return builder.sources(FrontApplication.class);
    }
}