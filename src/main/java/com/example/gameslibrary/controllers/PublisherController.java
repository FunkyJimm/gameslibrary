package com.example.gameslibrary.controllers;

import com.example.gameslibrary.commands.PublisherCommand;
import com.example.gameslibrary.converters.PublisherCommandToCommand;
import com.example.gameslibrary.model.Publisher;
import com.example.gameslibrary.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PublisherController {

    PublisherRepository publisherRepository;
    PublisherCommandToCommand publisherCommandToCommand;

    public PublisherController(PublisherRepository publisherRepository, PublisherCommandToCommand publisherCommandToCommand) {
        this.publisherRepository = publisherRepository;
        this.publisherCommandToCommand = publisherCommandToCommand;
    }

    @RequestMapping("/publishers")
    public String getPublishers(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());
        return "publisher/list";
    }

    @GetMapping("publishers/new")
    public String newPublisher(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/addedit";
    }

    @GetMapping("publishers/{id}/show")
    public String showPublisher(Model model, @PathVariable("id") Long id) {
        model.addAttribute("publishers", publisherRepository.findById(id).get());
        return "publisher/show";
    }

    @GetMapping("publishers/{id}/edit")
    public String editPublisher(Model model, @PathVariable("id") Long id) {
        model.addAttribute("publisher", publisherRepository.findById(id).get());
        return "publisher/addedit";
    }

    @RequestMapping("publishers/{id}/delete")
    public String deletePublisher(@PathVariable("id") Long id) {
        try {
            publisherRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Klucz, który chcesz usunąć jest powiązany z innym rekordem!");
            return "redirect:/error";
        }
        return "redirect:/publishers";
    }

    @PostMapping("publisher")
    public String saveOrUpdate(@ModelAttribute PublisherCommand command) {
        Optional<Publisher> gameOptional = publisherRepository.getFirstByName(command.getName());

        if(!gameOptional.isPresent()) {
            Publisher detachedPublisher = publisherCommandToCommand.convert(command);
            Publisher newPublisher = publisherRepository.save(detachedPublisher);
            return "redirect:/publishers/" + newPublisher.getId() + "/show";
        } else {
            Publisher editedPublisher = publisherCommandToCommand.convert(command);
            Publisher updatedPublisher = publisherRepository.save(editedPublisher);
            return "redirect:/publishers/" + updatedPublisher.getId() + "/show";
        }
    }
}
