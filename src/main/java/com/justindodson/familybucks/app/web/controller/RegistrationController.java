package com.justindodson.familybucks.app.web.controller;

import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import com.justindodson.familybucks.app.service.user.FamilyService;
import com.justindodson.familybucks.app.service.user.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    @Autowired
    private ParentService parentService;
    @Autowired
    private FamilyService familyService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        Family family = new Family();
        Parent parent = new Parent();
        model.addAttribute("family", family);
        model.addAttribute("parent", parent);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute Family family, @ModelAttribute Parent parent) {
        familyService.createNewFamily(family);
        parentService.createOrUpdateParent(parent);
        familyService.updateFamily(family.getId(), parent.getId());

        return "redirect:/parents/home/" + parent.getUsername();
    }
}
