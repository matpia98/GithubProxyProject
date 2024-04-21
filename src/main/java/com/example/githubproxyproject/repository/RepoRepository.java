package com.example.githubproxyproject.repository;

import com.example.githubproxyproject.model.Repo;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RepoRepository extends Repository<Repo, Long> {
    Repo save(Repo repo);

    void deleteById(Long id);

    List<Repo> findAll();

    Optional<Repo> findById(Long id);

    boolean existsById(Long id);
}
