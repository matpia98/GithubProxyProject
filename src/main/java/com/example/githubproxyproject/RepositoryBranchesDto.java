package com.example.githubproxyproject;

import java.util.List;

public record RepositoryBranchesDto(String name, String login, List<BranchDto> branches) {
}
