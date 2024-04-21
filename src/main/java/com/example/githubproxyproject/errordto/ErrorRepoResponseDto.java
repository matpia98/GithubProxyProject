package com.example.githubproxyproject.errordto;

import org.springframework.http.HttpStatus;

public record ErrorRepoResponseDto(String message, HttpStatus status) {
}
