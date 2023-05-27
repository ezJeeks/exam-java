package com.example.exam.controllers;

import com.example.exam.domain.dao.IFacultyRepository;
import com.example.exam.domain.models.Faculty;
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
public class FacultyController {

    private final IFacultyRepository _faculties;

    @RequestMapping("/faculties")
    public ModelAndView Faculties() {

        return new ModelAndView("faculty/faculties", "faculties", _faculties.findAll());
    }

    @GetMapping("/faculty/add")
    public String newFaculty(Model model){
        model.addAttribute("faculty", new Faculty());
        return "faculty/add";
    }

    @PostMapping("/faculty/add")
    public String addFaculty(@ModelAttribute("faculty") Faculty faculty) {
        _faculties.save(faculty);
        return "redirect:/faculties";
    }
}
