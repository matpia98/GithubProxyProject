package com.example.githubproxyproject.service;

import com.example.githubproxyproject.repository.RepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RepoDeleter {

    RepoRepository repoRepository;

    RepoDeleter(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public void deleteById(Long id) {
        log.info("deleting repo by id: " + id);
        repoRepository.deleteById(id);
    }
}
