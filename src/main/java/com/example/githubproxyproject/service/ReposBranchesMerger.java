package com.example.githubproxyproject.service;

import com.example.githubproxyproject.dtos.BranchDto;
import com.example.githubproxyproject.dtos.GetAllReposDto;
import com.example.githubproxyproject.dtos.RepositoryBranchesDto;
import com.example.githubproxyproject.proxy.GithubProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReposBranchesMerger {

    GithubProxy githubProxy;

    ReposBranchesMerger(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

    public List<RepositoryBranchesDto> mergeReposAndBranches(List<GetAllReposDto> repos) {
        return repos.stream()
                .map(repo -> {
                    List<BranchDto> branches = githubProxy.fetchBranches(repo.owner().login(), repo.name());
                    return new RepositoryBranchesDto(repo.name(), repo.owner().login(), branches);
                })
                .collect(Collectors.toList());
    }
}
