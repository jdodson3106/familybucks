package com.justindodson.familybucks.app.web.controller;

import com.justindodson.familybucks.app.model.entity.user.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parents")
public class ParentController {

    @GetMapping("/new")
    public String newParent(Model model) {
        Parent parent = new Parent();
        model.addAttribute("parent", parent);

        return "user/new_parent";
    }
}
