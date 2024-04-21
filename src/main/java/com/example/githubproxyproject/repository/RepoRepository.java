package com.example.githubproxyproject.repository;

import com.example.githubproxyproject.model.Repo;
import org.springframework.data.repository.Repository;

public interface RepoRepository extends Repository<Repo, Long> {
    Repo save(Repo repo);
}
