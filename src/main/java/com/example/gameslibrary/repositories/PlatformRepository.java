package com.example.gameslibrary.repositories;

import com.example.gameslibrary.model.Platform;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlatformRepository extends CrudRepository<Platform, Long> {
    Optional<Platform> getFirstByName(String name);
}
