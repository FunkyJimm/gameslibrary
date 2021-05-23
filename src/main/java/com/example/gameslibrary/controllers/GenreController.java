package com.example.gameslibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenreController {
    @RequestMapping("/genres")
    public String getGenres(Model model) {
        return "genres";
    }
}
