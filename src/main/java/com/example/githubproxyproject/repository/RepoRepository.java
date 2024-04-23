package com.example.githubproxyproject.repository;

import com.example.githubproxyproject.model.Repo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RepoRepository extends Repository<Repo, Long> {
    Repo save(Repo repo);

    void deleteById(Long id);

    List<Repo> findAll(Pageable pageable);

    Optional<Repo> findById(Long id);

    boolean existsById(Long id);

    @Modifying
    @Query("UPDATE Repo r SET r.owner = :#{#newRepo.owner}, r.name = :#{#newRepo.name} WHERE r.id = :id")
    void updateById(Long id, Repo newRepo);
}
