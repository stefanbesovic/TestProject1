package com.practice.test1.web.controllers;

import com.practice.test1.jwt.UsernameAndPasswordAuthRequest;
import com.practice.test1.web.dto.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
