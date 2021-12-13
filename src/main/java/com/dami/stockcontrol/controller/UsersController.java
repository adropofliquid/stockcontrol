package com.dami.stockcontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsersController {

    @GetMapping("/users")
    public String home(Model model){

        return "users";
    }

}