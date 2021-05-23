package com.example.gameslibrary.controllers;

import com.example.gameslibrary.commands.GenreCommand;
import com.example.gameslibrary.converters.GenreCommandToGenre;
import com.example.gameslibrary.model.Genre;
import com.example.gameslibrary.repositories.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GenreController {

    private GenreRepository genreRepository;
    private GenreCommandToGenre genreCommandToGenre;

    public GenreController(GenreRepository genreRepository, GenreCommandToGenre genreCommandToGenre) {
        this.genreRepository = genreRepository;
        this.genreCommandToGenre = genreCommandToGenre;
    }

    @RequestMapping("/genres")
    public String getGenres(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "genre/list";
    }

    @GetMapping("genres/new")
    public String newGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre/addedit";
    }

    @GetMapping("genres/{id}/show")
    public String showGenre(Model model, @PathVariable("id") Long id) {
        model.addAttribute("genres", genreRepository.findById(id).get());
        return "genre/show";
    }

    @GetMapping("genres/{id}/edit")
    public String editGenre(Model model, @PathVariable("id") Long id) {
        model.addAttribute("genre", genreRepository.findById(id).get());
        return "genre/addedit";
    }

    @RequestMapping("genres/{id}/delete")
    public String deleteGenre(@PathVariable("id") Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Klucz, który chcesz usunąć jest powiązany z innym rekordem!");
            return "redirect:/error";
        }
        return "redirect:/genres";
    }

    @PostMapping("genre")
    public String saveOrUpdate(@ModelAttribute GenreCommand command) {
        Optional<Genre> gameOptional = genreRepository.getFirstByName(command.getName());

        if(!gameOptional.isPresent()) {
            Genre detachedGenre = genreCommandToGenre.convert(command);
            Genre newGenre = genreRepository.save(detachedGenre);
            return "redirect:/genres/" + newGenre.getId() + "/show";
        } else {
            Genre editedGenre = genreCommandToGenre.convert(command);
            Genre updatedGenre = genreRepository.save(editedGenre);
            return "redirect:/genres/" + updatedGenre.getId() + "/show";
        }
    }
}
