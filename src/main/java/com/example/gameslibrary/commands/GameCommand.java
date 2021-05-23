package com.example.gameslibrary.commands;

import com.example.gameslibrary.model.Genre;
import com.example.gameslibrary.model.Publisher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameCommand {
    private Long id;
    private String title;
    private String description;
    private String year;
    private Genre genre;
    private Publisher publisher;
}
