package com.example.gameslibrary.repositories;

import com.example.gameslibrary.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> getFirstByName(String name);
}
