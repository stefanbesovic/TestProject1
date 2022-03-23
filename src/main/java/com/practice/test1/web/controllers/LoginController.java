package com.practice.test1.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
