package com.example.gameslibrary.converters;

import com.example.gameslibrary.commands.GenreCommand;
import com.example.gameslibrary.model.Genre;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GenreCommandToGenre implements Converter<GenreCommand, Genre> {

    @Synchronized
    @Nullable
    @Override
    public Genre convert(GenreCommand source) {
        if(source == null) {
            return null;
        }

        final Genre genre = new Genre();
        if(source.getId() != null) {
            genre.setId(source.getId());
        }
        genre.setName(source.getName());
        genre.setDescription(source.getDescription());
        return genre;
    }
}
