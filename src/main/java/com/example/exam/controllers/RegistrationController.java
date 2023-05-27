package com.example.exam.controllers;

import com.example.exam.domain.services.UserService;
import com.example.exam.domain.models.identity.AppUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService _userService;

//    @GetMapping("/registration")
//    public ModelAndView getRegistration(){
//        return new ModelAndView("user/registration","user",new AppUser());
//    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid AppUser appUser, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
//            return "user/registration";
            return "user/login";
        }
        if(!appUser.getPassword().equals(appUser.getConfirmPassword())){
            model.addAttribute("passwordError", "Invalid password");
//            return "user/registration";
            return "user/login";
        }
        if(!_userService.registerUser(appUser)){
            model.addAttribute("usernameForm","Username already exists");
//            return "user/registration";
            return "user/login";
        }
        return "user/login";
    }
}
