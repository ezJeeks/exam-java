package com.example.exam.controllers;

import com.example.exam.domain.dao.IDepartmentRepository;
import com.example.exam.domain.dao.ITeacherRepository;
import com.example.exam.domain.models.Department;
import com.example.exam.domain.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final ITeacherRepository _teacher;
    private final IDepartmentRepository _dep;

    @RequestMapping("/teachers")
    public ModelAndView Teachers() {

        return new ModelAndView("teacher/teachers", "teachers", _teacher.findAll());
    }

    @GetMapping("/teacher/add")
    public String newTeacher(Model model){
        List<Department> departments = _dep.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("teacher", new Teacher());
        return "teacher/add";
    }

    @PostMapping("/teacher/add")
    public String addTeacher(@ModelAttribute("teacher") Teacher teacher) {
        _teacher.save(teacher);
        return "redirect:/teachers";
    }
}
