package com.example.gameslibrary.converters;

import com.example.gameslibrary.commands.GameCommand;
import com.example.gameslibrary.model.Game;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GameCommandToGame implements Converter<GameCommand, Game> {

    @Synchronized
    @Nullable
    @Override
    public Game convert(GameCommand source) {
        if(source == null) {
            return null;
        }

        final Game game = new Game();
        if(source.getId() != null) {
            game.setId(source.getId());
        }
        game.setTitle(source.getTitle());
        game.setDescription(source.getDescription());
        game.setYear(source.getYear());
        game.setGenre(source.getGenre());
        game.setPublisher(source.getPublisher());
        return game;
    }
}
