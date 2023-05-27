package com.example.exam.controllers;

import com.example.exam.domain.models.identity.AppUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/user/login", "user",new AppUser());
    }

    @GetMapping("/hello")
    @Secured("USER")
    public ModelAndView hello(){
        return new ModelAndView("/hello", "data",new Object());
    }

    @GetMapping("/home")
    @Secured("USER")
    public ModelAndView home(){
        return new ModelAndView("/home", "data",new Object());
    }


//    @GetMapping("/registration")
//    public ModelAndView getRegistration(){
//        return new ModelAndView("user/login","user",new AppUser());
//    }

}
