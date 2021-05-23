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
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String producer;
    private String generation;

    @ManyToMany(mappedBy = "platforms")
    private Set<Game> games = new HashSet<>();

    public Platform(String name, String type, String producer, String generation) {
        this.name = name;
        this.type = type;
        this.producer = producer;
        this.generation = generation;
    }
}
