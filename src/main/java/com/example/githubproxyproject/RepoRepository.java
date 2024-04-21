package com.example.githubproxyproject;

import org.springframework.data.repository.Repository;

public interface RepoRepository extends Repository<Repo, Long> {
    Repo save(Repo repo);
}
