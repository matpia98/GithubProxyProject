package com.example.githubproxyproject.exceptions;

public class RepoNotFoundException extends RuntimeException {
    public RepoNotFoundException(String message) {
        super(message);
    }
}
