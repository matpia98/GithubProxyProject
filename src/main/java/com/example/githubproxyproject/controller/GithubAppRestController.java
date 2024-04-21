package com.example.githubproxyproject.controller;

import com.example.githubproxyproject.dtos.response.DeleteRepoResponseDto;
import com.example.githubproxyproject.model.Repo;
import com.example.githubproxyproject.dtos.request.CreateRepoRequestDto;
import com.example.githubproxyproject.dtos.response.CreateRepoResponseDto;
import com.example.githubproxyproject.service.RepoAdder;
import com.example.githubproxyproject.dtos.BranchDto;
import com.example.githubproxyproject.dtos.GetAllReposDto;
import com.example.githubproxyproject.proxy.GithubProxy;
import com.example.githubproxyproject.dtos.RepositoryBranchesDto;
import com.example.githubproxyproject.service.RepoDeleter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class GithubAppRestController {

    GithubProxy githubProxy;
    RepoAdder repoAdder;
    RepoDeleter repoDeleter;

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

    @DeleteMapping("/repos/{id}")
    public ResponseEntity<DeleteRepoResponseDto> deleteRepoByUsingIdPathVariable(@PathVariable Long id) {
        repoDeleter.deleteById(id);
        DeleteRepoResponseDto deleteRepoResponseDto = RepoMapper.mapFromRepoToDeleteRepoResponseDto(id);
        return ResponseEntity.ok(deleteRepoResponseDto);
    }
}
