package com.example.githubproxyproject.controller;

import com.example.githubproxyproject.dtos.request.UpdateRepoRequestDto;
import com.example.githubproxyproject.dtos.request.UpdateRepoResponseDto;
import com.example.githubproxyproject.dtos.response.DeleteRepoResponseDto;
import com.example.githubproxyproject.dtos.response.GetAllReposResponseDto;
import com.example.githubproxyproject.dtos.response.GetRepoResponseDto;
import com.example.githubproxyproject.model.Repo;
import com.example.githubproxyproject.dtos.request.CreateRepoRequestDto;
import com.example.githubproxyproject.dtos.response.CreateRepoResponseDto;
import com.example.githubproxyproject.service.*;
import com.example.githubproxyproject.dtos.GetAllReposDto;
import com.example.githubproxyproject.proxy.GithubProxy;
import com.example.githubproxyproject.dtos.RepositoryBranchesDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class GithubAppRestController {

    GithubProxy githubProxy;
    RepoAdder repoAdder;
    RepoDeleter repoDeleter;
    RepoRetriever repoRetriever;
    RepoUpdater repoUpdater;
    ReposBranchesMerger reposBranchesMerger;

    @GetMapping(value = "/{username}")
    public ResponseEntity<List<RepositoryBranchesDto>> getAllReposFromUser(@PathVariable("username") String username) {
        List<GetAllReposDto> repos = githubProxy.fetchAllRepos(username);
        List<RepositoryBranchesDto> reposAndBranches = reposBranchesMerger.mergeReposAndBranches(repos);
        repoAdder.addAllRepos(reposAndBranches);
        return ResponseEntity.ok(reposAndBranches);
    }

    @PostMapping(value = "/repos")
    public ResponseEntity<CreateRepoResponseDto> postRepo(@RequestBody CreateRepoRequestDto createRepoRequestDto) {
        Repo repo = RepoMapper.mapFromCreateRepoRequestDtoToRepo(createRepoRequestDto);
        Repo savedRepo = repoAdder.addRepo(repo);
        CreateRepoResponseDto body = RepoMapper.mapFromRepoToCreateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping(value = "/repos/{id}")
    public ResponseEntity<DeleteRepoResponseDto> deleteRepoByUsingIdPathVariable(@PathVariable Long id) {
        repoDeleter.deleteById(id);
        DeleteRepoResponseDto deleteRepoResponseDto = RepoMapper.mapFromRepoToDeleteRepoResponseDto(id);
        return ResponseEntity.ok(deleteRepoResponseDto);
    }

    @GetMapping(value = "/repos")
    public ResponseEntity<GetAllReposResponseDto> getAllReposFromDatabase() {
        List<Repo> allRepos = repoRetriever.findAll();
        GetAllReposResponseDto getAllReposResponseDto = RepoMapper.mapFromRepoToGetAllReposResponseDto(allRepos);
        return ResponseEntity.ok(getAllReposResponseDto);
    }

    @GetMapping(value = "/repos/{id}")
    public ResponseEntity<GetRepoResponseDto> getRepoById(@PathVariable Long id) {
        Repo repo = repoRetriever.findRepoById(id);
        GetRepoResponseDto response = RepoMapper.mapFromRepoToGetRepoResponseDto(repo);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/repos/{id}")
    public ResponseEntity<UpdateRepoResponseDto> updateRepoById(@PathVariable Long id,
                                                                @RequestBody UpdateRepoRequestDto request) {
        Repo newRepo = RepoMapper.mapFromUpdateRepoRequestDtoToRepo(request);
        repoUpdater.updateById(id, newRepo);
        UpdateRepoResponseDto response = RepoMapper.mapFromRepotoUpdateRepoResponseDto(id, newRepo);
        return ResponseEntity.ok(response);
    }
}
