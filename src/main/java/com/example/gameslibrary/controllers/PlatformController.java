package com.example.gameslibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlatformController {
    @RequestMapping("/platforms")
    public String getPlatforms(Model model) {
        return "platforms";
    }
}
