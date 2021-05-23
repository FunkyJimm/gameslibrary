package com.example.gameslibrary.repositories;

import com.example.gameslibrary.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
