package com.example.gameslibrary.controllers;

import com.example.gameslibrary.commands.PlatformCommand;
import com.example.gameslibrary.converters.PlatformCommandToPlatform;
import com.example.gameslibrary.model.Platform;
import com.example.gameslibrary.repositories.PlatformRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PlatformController {

    PlatformRepository platformRepository;
    PlatformCommandToPlatform platformCommandToPlatform;

    public PlatformController(PlatformRepository platformRepository, PlatformCommandToPlatform platformCommandToPlatform) {
        this.platformRepository = platformRepository;
        this.platformCommandToPlatform = platformCommandToPlatform;
    }

    @RequestMapping("/platforms")
    public String getPlatforms(Model model) {
        model.addAttribute("platforms", platformRepository.findAll());
        return "platform/list";
    }

    @GetMapping("platforms/new")
    public String newPlatform(Model model) {
        model.addAttribute("platforms", new Platform());
        return "platform/addedit";
    }

    @GetMapping("platforms/{id}/show")
    public String showPlatform(Model model, @PathVariable("id") Long id) {
        model.addAttribute("platforms", platformRepository.findById(id).get());
        return "platform/show";
    }

    @GetMapping("platforms/{id}/edit")
    public String editPlatform(Model model, @PathVariable("id") Long id) {
        model.addAttribute("platform", platformRepository.findById(id).get());
        return "platform/addedit";
    }

    @RequestMapping("platforms/{id}/delete")
    public String deletePlatform(@PathVariable("id") Long id) {
        try {
            platformRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Klucz, który chcesz usunąć jest powiązany z innym rekordem!");
            return "redirect:/error";
        }
        return "redirect:/platforms";
    }

    @PostMapping("platform")
    public String saveOrUpdate(@ModelAttribute PlatformCommand command) {
        Optional<Platform> gameOptional = platformRepository.getFirstByName(command.getName());

        if(!gameOptional.isPresent()) {
            Platform detachedPlatform = platformCommandToPlatform.convert(command);
            Platform newPlatform = platformRepository.save(detachedPlatform);
            return "redirect:/platforms/" + newPlatform.getId() + "/show";
        } else {
            Platform editedPlatform = platformCommandToPlatform.convert(command);
            Platform updatedPlatform = platformRepository.save(editedPlatform);
            return "redirect:/platforms/" + updatedPlatform.getId() + "/show";
        }
    }
}
