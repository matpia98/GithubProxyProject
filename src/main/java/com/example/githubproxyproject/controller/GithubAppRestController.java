package com.example.githubproxyproject.controller;

import com.example.githubproxyproject.Repo;
import com.example.githubproxyproject.RepoAdder;
import com.example.githubproxyproject.dtos.BranchDto;
import com.example.githubproxyproject.dtos.GetAllReposDto;
import com.example.githubproxyproject.proxy.GithubProxy;
import com.example.githubproxyproject.dtos.RepositoryBranchesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GithubAppRestController {

    GithubProxy githubProxy;
    RepoAdder repoAdder;

    @Autowired
    public GithubAppRestController(GithubProxy githubProxy, RepoAdder repoAdder) {
        this.githubProxy = githubProxy;
        this.repoAdder = repoAdder;
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
        reposAndBranches
                .forEach(repositoryBranch -> {
                    Repo repo = new Repo(repositoryBranch.login(), repositoryBranch.name());
                    repoAdder.addRepo(repo);
                });
        return ResponseEntity.ok(reposAndBranches);
    }

    @PostMapping(value = "/repos")
    public ResponseEntity<CreateRepoResponseDto> postRepo(@RequestBody CreateRepoRequestDto createRepoRequestDto) {
        Repo repo = RepoMapper.mapFromCreateRepoRequestDtoToRepo(createRepoRequestDto);
        Repo savedRepo = repoAdder.addRepo(repo);
        CreateRepoResponseDto body = RepoMapper.mapFromRepoToCreateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(body);
    }
}
