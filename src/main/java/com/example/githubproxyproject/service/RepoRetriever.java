package com.example.githubproxyproject.service;

import com.example.githubproxyproject.model.Repo;
import com.example.githubproxyproject.repository.RepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RepoRetriever {

    private final RepoRepository repoRepository;

    public RepoRetriever(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public List<Repo> findAll() {
        log.info("Retrieving all repos from database:");
        return repoRepository.findAll();
    }
}
