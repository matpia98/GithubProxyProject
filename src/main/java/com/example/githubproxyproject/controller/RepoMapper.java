package com.example.githubproxyproject.controller;

import com.example.githubproxyproject.dtos.response.DeleteRepoResponseDto;
import com.example.githubproxyproject.dtos.response.GetAllReposResponseDto;
import com.example.githubproxyproject.dtos.response.GetRepoResponseDto;
import com.example.githubproxyproject.model.Repo;
import com.example.githubproxyproject.dtos.request.CreateRepoRequestDto;
import com.example.githubproxyproject.dtos.response.CreateRepoResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class RepoMapper {

    public static Repo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto createRepoRequestDto) {
        return new Repo(createRepoRequestDto.owner(), createRepoRequestDto.name());
    }

    public static CreateRepoResponseDto mapFromRepoToCreateRepoResponseDto(Repo savedRepo) {
        return new CreateRepoResponseDto(savedRepo.getId(), savedRepo.getOwner(), savedRepo.getName());
    }

    public static DeleteRepoResponseDto mapFromRepoToDeleteRepoResponseDto(Long id) {
        return new DeleteRepoResponseDto("Removed repo with id: " + id, HttpStatus.OK);
    }

    public static GetAllReposResponseDto mapFromRepoToGetAllReposResponseDto(List<Repo> allRepos) {
        return new GetAllReposResponseDto(allRepos);
    }

    public static GetRepoResponseDto mapFromRepoToGetRepoResponseDto(Repo repo) {
        return new GetRepoResponseDto(repo.getId(), repo.getOwner(), repo.getName());
    }
}
