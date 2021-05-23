package com.example.gameslibrary.controllers;

import com.example.gameslibrary.commands.GameCommand;
import com.example.gameslibrary.converters.GameCommandToGame;
import com.example.gameslibrary.model.Game;
import com.example.gameslibrary.repositories.GameRepository;
import com.example.gameslibrary.repositories.GenreRepository;
import com.example.gameslibrary.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GameController {

    private GameRepository gameRepository;
    private GenreRepository genreRepository;
    private PublisherRepository publisherRepository;
    private GameCommandToGame gameCommandToGame;

    public GameController(GameRepository gameRepository, GenreRepository genreRepository, PublisherRepository publisherRepository, GameCommandToGame gameCommandToGame) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.gameCommandToGame = gameCommandToGame;
    }

    @RequestMapping("/games")
    public String getGames(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "game/list";
    }

    @GetMapping("games/new")
    public String newGame(Model model) {
        model.addAttribute("game", new Game());
        if(genreRepository != null) {
            model.addAttribute("genres", genreRepository.findAll());
        }
        if(publisherRepository != null) {
            model.addAttribute("publishers", publisherRepository.findAll());
        }
        return "game/addedit";
    }

    @GetMapping("games/{id}/show")
    public String showGame(Model model, @PathVariable("id") Long id) {
        model.addAttribute("games", gameRepository.findById(id).get());
        return "game/show";
    }

    @GetMapping("games/{id}/edit")
    public String editGame(Model model, @PathVariable("id") Long id) {
        model.addAttribute("game", gameRepository.findById(id).get());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "game/addedit";
    }

    @RequestMapping("games/{id}/delete")
    public String deleteGame(@PathVariable("id") Long id) {
        try {
            gameRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Klucz, który chcesz usunąć jest powiązany z innym rekordem!");
            return "redirect:/error";
        }
        return "redirect:/games";
    }

    @PostMapping("game")
    public String saveOrUpdate(@ModelAttribute GameCommand command) {
        Optional<Game> gameOptional = gameRepository.getFirstByTitle(command.getTitle());

        if(!gameOptional.isPresent()) {
            Game detachedGame = gameCommandToGame.convert(command);
            Game newGame = gameRepository.save(detachedGame);
            return "redirect:/games/" + newGame.getId() + "/show";
        } else {
            Game editedGame = gameCommandToGame.convert(command);
            Game updatedGame = gameRepository.save(editedGame);
            return "redirect:/games/" + updatedGame.getId() + "/show";
        }
    }
}
