package com.example.gameslibrary.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String year;
    @ManyToOne
    private Genre genre;
    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private Set<Platform> platforms = new HashSet<>();

    public Game() {
    }

    public Game(String title, String description, String year, Genre genre, Publisher publisher) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", genre=" + genre +
                ", publisher=" + publisher +
                ", platforms=" + platforms +
                '}';
    }
}
