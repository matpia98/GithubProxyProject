package com.example.githubproxyproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
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
