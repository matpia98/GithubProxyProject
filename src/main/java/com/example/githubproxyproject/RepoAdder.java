package com.example.githubproxyproject;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
}
