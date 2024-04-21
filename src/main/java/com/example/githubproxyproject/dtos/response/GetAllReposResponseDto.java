package com.example.githubproxyproject.dtos.response;

import com.example.githubproxyproject.model.Repo;

import java.util.List;

public record GetAllReposResponseDto(List<Repo> allRepos) {
}
