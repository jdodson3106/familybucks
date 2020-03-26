package com.justindodson.familybucks.bootstrap;

import com.justindodson.familybucks.accounts.auth.UserRepository;
import com.justindodson.familybucks.accounts.model.entity.user.Child;
import com.justindodson.familybucks.accounts.model.entity.user.Family;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import com.justindodson.familybucks.accounts.model.repository.user.FamilyRepository;
import com.justindodson.familybucks.accounts.service.ParentServiceImpl;
import com.justindodson.familybucks.products.model.entity.Product;
import com.justindodson.familybucks.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final ParentServiceImpl parentServiceImpl;
    private final ProductService productService;

    @Autowired
    public BootstrapData(UserRepository userRepository, FamilyRepository familyRepository, ParentServiceImpl parentServiceImpl, ProductService productService) {
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
        this.parentServiceImpl = parentServiceImpl;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        Family testFam = new Family("JD Family");
        Parent user = new Parent("admin", "Justin", "Dodson", "asdf", "asdf", testFam, "");
        testFam.familyMembers.add(user);
        parentServiceImpl.createOrUpdateParent(user);
        familyRepository.save(testFam);

        Family family = new Family("ThDodsons");
        Parent parent = new Parent("jd", "Justin", "Dodson", "password", "password", family, "");
        Parent parent2 = new Parent("DD", "Diana", "Dodson", "password", "password", family, "");
        Child child = new Child("WD", "Wyatt", "Dodson", "password", "password", family);
        Child child2 = new Child("ND", "Noah", "Dodson", "password", "password", family);
        Child child3 = new Child("LD", "Lilah", "Dodson", "password", "password", family);
        Child child4 = new Child("SD", "Skylar", "Dodson", "password", "password", family);;
        family.familyMembers.add(parent);
        family.familyMembers.add(parent2);
        family.familyMembers.add(child);
        family.familyMembers.add(child2);
        family.familyMembers.add(child3);
        family.familyMembers.add(child4);
        parentServiceImpl.createOrUpdateParent(parent);
        parentServiceImpl.createOrUpdateParent(parent2);
        familyRepository.save(family);
        userRepository.save(child);
        userRepository.save(child2);
        userRepository.save(child3);
        userRepository.save(child4);

        Family family2 = new Family("TheJoiners");
        Parent parent3 = new Parent("LJ", "Lance", "Joiner", "password", "password", family2, "");
        Parent parent4 = new Parent("MJ", "Meagan", "Joiner", "password", "password", family2, "");
        Child child5 = new Child("EJ", "Emma", "Joiner", "password", "password", family2);
        Child child6 = new Child("HD", "Harley", "Davison", "password", "password", family2);
        Child child7 = new Child("BD", "Broox", "Davison", "password", "password", family2);
        Child child8 = new Child("KJ", "Kaydence", "Joiner", "password", "password", family2);
        family2.familyMembers.add(parent3);
        family2.familyMembers.add(parent4);
        family2.familyMembers.add(child5);
        family2.familyMembers.add(child6);
        family2.familyMembers.add(child7);
        family2.familyMembers.add(child8);
        parentServiceImpl.createOrUpdateParent(parent3);
        parentServiceImpl.createOrUpdateParent(parent4);
        familyRepository.save(family2);
        userRepository.save(child5);
        userRepository.save(child6);
        userRepository.save(child7);
        userRepository.save(child8);

        Product product = new Product();
        product.setName("Random");
        product.setDescription("asd;lkasdlkfjal;dskfja;dlkfjal;dksfj");
        product.setCost(24.55);
        product.setOwnerID(1);
        productService.createOrUpdateProduct(product);

    }
}
