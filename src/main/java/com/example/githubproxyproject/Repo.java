package com.example.githubproxyproject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repo")
public class Repo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String owner;

    @Column
    private String name;

    public Repo() {
    }

    public Repo(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }
}
