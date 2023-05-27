package com.example.exam.controllers;

import com.example.exam.domain.dao.IFacultyRepository;
import com.example.exam.domain.dao.IGroupRepository;
import com.example.exam.domain.models.Faculty;
import com.example.exam.domain.models.Group;
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
public class GroupController {

    private final IGroupRepository _groups;
    private final IFacultyRepository _faculties;

    @RequestMapping("/groups")
    public ModelAndView Groups() {

        return new ModelAndView("group/groups", "groups", _groups.findAll());
    }

    @GetMapping("/group/add")
    public String newGroup(Model model){
        Iterable<Faculty> faculties = _faculties.findAll();
        model.addAttribute("faculties", faculties);
        model.addAttribute("group", new Group());
        return "group/add";
    }

    @PostMapping("/group/add")
    public String addGroup(@ModelAttribute("group") Group group) {
        _groups.save(group);
        return "redirect:/groups";
    }
}

