package com.example.gameslibrary.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Lob
    private String description;
    private String year;
    @ManyToOne
    private Genre genre;
    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private Set<Platform> platforms = new HashSet<>();

    public Game(String title, String description, String year, Genre genre, Publisher publisher) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
    }
}
