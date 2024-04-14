package com.example.githubproxyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubProxyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubProxyProjectApplication.class, args);
	}
}
