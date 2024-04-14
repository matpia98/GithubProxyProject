package com.example.githubproxyproject.controller;

import com.example.githubproxyproject.dtos.BranchDto;
import com.example.githubproxyproject.dtos.GetAllReposDto;
import com.example.githubproxyproject.proxy.GithubProxy;
import com.example.githubproxyproject.dtos.RepositoryBranchesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GithubAppRestController {

    GithubProxy githubProxy;

    @Autowired
    public GithubAppRestController(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<List<RepositoryBranchesDto>> getAllReposFromUser(@PathVariable("username") String username) {
        List<GetAllReposDto> repos = githubProxy.fetchAllRepos(username);
        List<RepositoryBranchesDto> reposAndBranches = repos.stream()
                .map(repo -> {
                    List<BranchDto> branches = githubProxy.fetchBranches(repo.owner().login(), repo.name());
                    return new RepositoryBranchesDto(repo.name(), repo.owner().login(), branches);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(reposAndBranches);
    }
}
