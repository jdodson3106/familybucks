package com.justindodson.familybucks.products.service;

import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.products.model.entity.Product;
import com.justindodson.familybucks.products.model.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createOrUpdateProduct(Product product) {
        Optional<Product> foundProduct = productRepository.findById(product.getId());

        if(foundProduct.isPresent()) {
            LOGGER.info("Updating product: " + foundProduct.get().toString());
            Product updated = foundProduct.get();
            updated.setName(product.getName());
            updated.setDescription(product.getDescription());
            updated.setCost(product.getCost());
            updated.setOwnerID(product.getOwnerID());
            return productRepository.save(updated);
        } else {
            LOGGER.info("Created new product: " + product.toString());
            return productRepository.save(product);
        }
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if(foundProduct.isPresent()) {
            return foundProduct.get();
        }
        return null;
    }

    @Override
    public List<Product> getAllProductsByOwner(long ownerId) {
        List<Product> ownerProducts = new ArrayList<>();

        productRepository.findAll().forEach(product -> {
            if(product.getOwnerID() == ownerId) {
                ownerProducts.add(product);
            }
        });

        return ownerProducts;
    }
}
