package com.example.githubproxyproject.controller;

import com.example.githubproxyproject.Repo;

public class RepoMapper {

    public static Repo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto createRepoRequestDto) {
        return new Repo(createRepoRequestDto.owner(), createRepoRequestDto.name());
    }

    public static CreateRepoResponseDto mapFromRepoToCreateRepoResponseDto(Repo savedRepo) {
        return new CreateRepoResponseDto(savedRepo.getId(), savedRepo.getOwner(), savedRepo.getName());
    }
}
