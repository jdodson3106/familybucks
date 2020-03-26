package com.justindodson.familybucks.products.service;

import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.products.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product createOrUpdateProduct(Product product);
    Product getProductById(long id);
    List<Product> getAllProductsByOwner(long ownerId);

}
