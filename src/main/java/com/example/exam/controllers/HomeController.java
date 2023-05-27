package com.example.exam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView Index(){

        return new ModelAndView("home/index", "data", new Object());
    }

    @RequestMapping("/about")
    public ModelAndView About(){

        return new ModelAndView("home/about", "data", new Object());
    }
}
