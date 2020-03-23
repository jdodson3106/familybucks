package com.justindodson.familybucks.accounts.web.controller;

import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.entity.user.Child;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import com.justindodson.familybucks.accounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String userDashboardView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        if (null != user) {
            if(user.getUserType().equalsIgnoreCase("parent")){
                Parent parent = userService.getParentByUserType(user.getUserType(), user.getId());
                model.addAttribute("parent", parent);
                return "users/parent_dashboard";
            }
            else if(user.getUserType().equalsIgnoreCase("child")){
                Child child = userService.getChildByUserType(user.getUserType(), user.getId());
                model.addAttribute("child", child);
                return "users/child_dashboard";
            }
        }
        return "redirect:/login";
    }
}
