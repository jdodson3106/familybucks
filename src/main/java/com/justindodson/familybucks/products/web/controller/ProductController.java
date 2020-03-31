package com.justindodson.familybucks.products.web.controller;

import com.justindodson.familybucks.CustomMessages;
import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import com.justindodson.familybucks.accounts.service.ChildService;
import com.justindodson.familybucks.accounts.service.ParentService;
import com.justindodson.familybucks.accounts.service.UserService;
import com.justindodson.familybucks.products.model.entity.Product;
import com.justindodson.familybucks.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ParentService parentService;
    private final ChildService childService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, ParentService parentService, ChildService childService, UserService userService) {
        this.productService = productService;
        this.parentService = parentService;
        this.childService = childService;
        this.userService = userService;
    }

    @GetMapping("/create-product")
    public String newProductView(Model model, Principal principal) {
        User currentUser = userService.getUserByUsername(principal.getName());
        if(currentUser != null) {
            if(currentUser.getUserType().equalsIgnoreCase("parent")) {
                Product product = new Product();
                model.addAttribute("product", product);

                return "products/add_product";
            } else {
                return "redirect:/dashboard";
            }
        }
        return "login";
    }

    @PostMapping("/create-product")
    public String newProductProcessor(Model model, @ModelAttribute Product product, Principal principal, RedirectAttributes attributes) {
        User currentUser = userService.getUserByUsername(principal.getName());

        product.setOwnerID(currentUser.getId());
        productService.createOrUpdateProduct(product);

        logger.info("Successfully created product: " + product.getNameForUri() + " in controller");

        attributes.addFlashAttribute(CustomMessages.SUCCESS, CustomMessages.PRODUCT_CREATION_SUCCESS_MESSAGE + " " + product.getName());

        return "redirect:/dashboard"; // TODO: 3/31/20 Create a dashboard for the products page to redirect to
    }

    @GetMapping("/dashboard")
    public String productsDashboard(Model model, Principal principal, RedirectAttributes attributes) {
        User currentUser = userService.getUserByUsername(principal.getName());

        if(currentUser != null) {
            if(currentUser.getUserType().equalsIgnoreCase("parent")) {
                List<Product> productList = productService.getAllProductsByOwner(currentUser.getId());
                model.addAttribute("products", productList);
                return "products/dashboard";
            }
            else{
                attributes.addFlashAttribute(CustomMessages.ERROR, CustomMessages.AUTHORIZATION_UNAUTHORIZED_ERROR_MESSAGE);
                return "redirect:/dashboard";
            }
        }
        return "login";
    }

}
