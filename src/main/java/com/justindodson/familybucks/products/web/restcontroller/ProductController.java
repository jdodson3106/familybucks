package com.justindodson.familybucks.products.web.restcontroller;

import com.justindodson.familybucks.CustomResponses;
import com.justindodson.familybucks.products.model.entity.Product;
import com.justindodson.familybucks.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/new")
    public Map<String, String> newProduct(@RequestBody Product product) {
        Map<String, String> messageMap = new HashMap<>();
        if(product != null){
            productService.createOrUpdateProduct(product);
            messageMap.put(CustomResponses.SUCCESS, "product " + product.getName() +  " successfully created");
        } else {
            messageMap.put(CustomResponses.ERROR, "Could not create null product");
        }
        return messageMap;
    }

    @GetMapping("/my-products")
    public List<Product> getProductByOwner(@RequestParam("ownerId") long ownerId) {
        return productService.getAllProductsByOwner(ownerId);
    }
}
