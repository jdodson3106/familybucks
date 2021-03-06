package com.justindodson.familybucks.accounts.web.controller;

import com.justindodson.familybucks.CustomMessages;
import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.entity.user.Child;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import com.justindodson.familybucks.accounts.service.ChildService;
import com.justindodson.familybucks.accounts.service.FamilyService;
import com.justindodson.familybucks.accounts.service.ParentService;
import com.justindodson.familybucks.products.model.entity.Product;
import com.justindodson.familybucks.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/parents")
@Slf4j
public class ParentController {
    public static final String SERVICEURL = "http://localhost:8080";

    private final Logger LOGGER = LoggerFactory.getLogger(ParentController.class);

    private final ParentService parentService;
    private final ChildService childService;
    private final FamilyService familyService;
    private final ProductService productService;

    @Autowired
    public ParentController(ParentService parentService, ChildService childService, FamilyService familyService, ProductService productService) {
        this.parentService = parentService;
        this.childService = childService;
        this.familyService = familyService;
        this.productService = productService;
    }

    // TODO: 3/20/20 !!!TESTING ONLY!!!: REMOVE THIS MAPPING TO SEE ALL PARENTS IN THE SYSTEM
    @GetMapping("/all")
    public String getAllParents(Model model) {
        List<Parent> parents = parentService.getAllParents();
        model.addAttribute("parents",parents);

        return "users/all_parents";
    }

    @GetMapping("/new")
    public String newParent(Model model) {
        Parent parent = new Parent();
        model.addAttribute("parent", parent);

        return "users/new_parent";
    }

    // used to add another parent to the family based on the logged in user's family
    @PostMapping("/new")
    public String newParentCreation(@ModelAttribute Parent person, Principal principal) {
        User currentUser = parentService.getParentByUsername(principal.getName());
        person.setFamily(currentUser.getFamily());
        parentService.createOrUpdateParent(person);

        return "redirect:/parents/my-family";
    }

    @GetMapping("/my-family")
    public String familyStructure(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = parentService.getParentByUsername(auth.getName());
        List<User> family = familyService.getAllFamilyMembers(user);
        model.addAttribute("family", family);
        model.addAttribute("family_name", user.getFamily().getFamilyName());

        return "users/my_family";
    }

    @GetMapping("/add-child")
    public String addChildView(Model model) {
        Child child = new Child();
        model.addAttribute("child", child);
        return "users/add_child";
    }

    @PostMapping("/add-child")
    public String addChildProcessor(Model model, @ModelAttribute("child") Child child, Principal principal) {
        User currentUser = parentService.getParentByUsername(principal.getName());
        child = childService.addChildToFamily(child, currentUser.getFamily());
        childService.createOrUpdateChild(child);
        return "redirect:/parents/my-family";
    }
}
