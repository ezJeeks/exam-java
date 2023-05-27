package com.example.exam.controllers;

import com.example.exam.domain.dao.IAuthorRepository;
import com.example.exam.domain.models.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final IAuthorRepository _repo;

    @RequestMapping("/author/list")
    public ModelAndView Author(){

        return new ModelAndView("author/list", "authors", _repo.findAuthorsByBooks(1));
    }

    @RequestMapping("/author")
    public ModelAndView Authors(){

        return new ModelAndView("author/authors", "authors", _repo.findAll());
    }

    @GetMapping("/author/add")
    public String newAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    @PostMapping("/authors")
    public String addAuthor(@ModelAttribute("author") Author author) {
        _repo.save(author);
        return "redirect:/author";
    }
}
