package com.example.gameslibrary.repositories;

import com.example.gameslibrary.model.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    Optional<Publisher> getFirstByName(String name);
}
