package com.example.githubproxyproject.dtos.github;

import java.util.List;

public record RepositoryBranchesDto(String name, String login, List<BranchDto> branches) {
}
