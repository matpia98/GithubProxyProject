package com.example.githubproxyproject.service;

import com.example.githubproxyproject.model.Repo;
import com.example.githubproxyproject.repository.RepoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RepoUpdater {

    private final RepoRepository repoRepository;
    private final RepoRetriever repoRetriever;

    RepoUpdater(RepoRepository repoRepository, RepoRetriever repoRetriever) {
        this.repoRepository = repoRepository;
        this.repoRetriever = repoRetriever;
    }

    @Transactional
    public void updateById(Long id, Repo newRepo) {
        repoRetriever.existsById(id);
        repoRepository.updateById(id, newRepo);
    }
}
