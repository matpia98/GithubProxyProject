package com.example.githubproxyproject.config;

import com.example.githubproxyproject.handlers.CustomErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private static final String AUTH_TOKEN = "token " + System.getenv("GITHUB_TOKEN");
    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", AUTH_TOKEN);
    }


}
