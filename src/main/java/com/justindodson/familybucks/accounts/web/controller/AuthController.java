package com.justindodson.familybucks.accounts.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/logout-success")
    public String getLogoutSuccess(Model model) {
        return "logout";
    }
}
