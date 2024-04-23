package com.example.githubproxyproject.proxy;

import com.example.githubproxyproject.config.FeignConfig;
import com.example.githubproxyproject.dtos.github.BranchDto;
import com.example.githubproxyproject.dtos.github.GetAllReposDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-proxy", configuration = FeignConfig.class)
public interface GithubProxy {

    @GetMapping("/users/{username}/repos")
    List<GetAllReposDto> fetchAllRepos(@PathVariable("username") String username);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> fetchBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
 }
