package com.example.githubproxyproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class GithubProxyProjectApplication {

	GithubProxy githubProxy;

	@Autowired
	public GithubProxyProjectApplication(GithubProxy githubProxy) {
		this.githubProxy = githubProxy;
	}

	public static void main(String[] args) {
		SpringApplication.run(GithubProxyProjectApplication.class, args);
	}

//	@EventListener(ApplicationStartedEvent.class)
//	public void start() {
//		List<GetAllReposDto> repos = githubProxy.fetchAllRepos("kalqa");
//		repos.forEach(log::info);
//	}

}
