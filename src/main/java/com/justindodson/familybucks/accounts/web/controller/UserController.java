package com.justindodson.familybucks.accounts.web.controller;

import com.justindodson.familybucks.CustomMessages;
import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.entity.user.Child;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import com.justindodson.familybucks.accounts.service.UserService;
import com.justindodson.familybucks.products.model.entity.Product;
import com.justindodson.familybucks.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService)
    {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/dashboard")
    public String userDashboardView(Model model, Principal principal, HttpServletRequest request) {
        Map<String, ?> messages = RequestContextUtils.getInputFlashMap(request);

        User user = userService.getUserByUsername(principal.getName());
        if (null != user) {
            if(user.getUserType().equalsIgnoreCase("parent")){
               if(messages != null) {
                   model.addAttribute("messages", messages);
               }
                Parent parent = userService.getParentByUserType(user.getUserType(), user.getId());
                List<Product> productList = productService.getAllProductsByOwner(parent.getId());
                if(productList.size() > 5) {
                    productList = productList.subList(0, 5);
                }
                model.addAttribute("parent", parent);
                model.addAttribute("products", productList);
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
