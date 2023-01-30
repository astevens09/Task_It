package com.project.taskit.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getLandingPage(){
        return "landingPage";
    }

    @GetMapping("/about")
    public String getAboutPage(){
        return "about";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "user/loginPage";
    }







}
