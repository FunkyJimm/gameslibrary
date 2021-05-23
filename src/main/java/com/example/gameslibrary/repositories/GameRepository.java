package com.example.gameslibrary.repositories;

import com.example.gameslibrary.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> getFirstByTitle(String title);
}
