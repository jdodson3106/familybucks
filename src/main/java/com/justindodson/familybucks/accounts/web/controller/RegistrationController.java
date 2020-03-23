package com.justindodson.familybucks.accounts.web.controller;

import com.justindodson.familybucks.accounts.model.entity.user.Family;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import com.justindodson.familybucks.accounts.service.FamilyMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final FamilyMemberService familyMemberService;

    @Autowired
    public RegistrationController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        Family family = new Family();
        Parent parent = new Parent();
        model.addAttribute("family", family);
        model.addAttribute("parent", parent);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(HttpServletRequest request, @ModelAttribute Family family, @ModelAttribute Parent parent) {
        familyMemberService.addUserToFamily(parent, family);
        return "redirect:/parents/home/" + parent.getUsername();
    }
}
