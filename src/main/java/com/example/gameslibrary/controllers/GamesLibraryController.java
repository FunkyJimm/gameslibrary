package com.example.gameslibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GamesLibraryController {

    @RequestMapping(value = {"/"})
    public String index() {
        return "index";
    }
}
