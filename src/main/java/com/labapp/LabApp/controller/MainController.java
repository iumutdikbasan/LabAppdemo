package com.labapp.LabApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/showMyLoginPage")/*Değiştirilebilir*/
    public String loginPage() {
        return "loginPage";
    }
    @GetMapping("/access-denied")/*Değiştirilebilir*/
    public String deniedPage() {
        return "access-denied";
    }
}
