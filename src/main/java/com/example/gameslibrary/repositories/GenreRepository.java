package com.example.gameslibrary.repositories;

import com.example.gameslibrary.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
