package com.justindodson.familybucks.accounts.web.controller;

import com.justindodson.familybucks.accounts.model.entity.user.Family;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BaseController {

    @GetMapping
    public String indexPage(Model model) {
        Parent parent = new Parent();
        Family family = new Family();
        model.addAttribute("parent", parent);
        model.addAttribute("family", family);

        return "index";
    }
}
