package com.example.githubproxyproject;


import org.springframework.stereotype.Service;

@Service
public class RepoAdder {

    private final RepoRepository repoRepository;

    RepoAdder(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public Repo addRepo(Repo repo) {
        return repoRepository.save(repo);
    }
}
