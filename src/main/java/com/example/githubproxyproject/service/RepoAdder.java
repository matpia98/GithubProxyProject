package com.example.githubproxyproject.service;


import com.example.githubproxyproject.dtos.RepositoryBranchesDto;
import com.example.githubproxyproject.model.Repo;
import com.example.githubproxyproject.repository.RepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RepoAdder {

    private final RepoRepository repoRepository;

    RepoAdder(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public Repo addRepo(Repo repo) {
        Repo savedRepo = repoRepository.save(repo);
        log.info("added new repo: " + savedRepo);
        return savedRepo;
    }

    public void addAllRepos(List<RepositoryBranchesDto> reposAndBranches) {
        reposAndBranches
                .forEach(repositoryBranch -> {
                    Repo repo = new Repo(repositoryBranch.login(), repositoryBranch.name());
                    addRepo(repo);
                });
    }
}
