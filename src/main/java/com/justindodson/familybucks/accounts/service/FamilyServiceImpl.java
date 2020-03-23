package com.justindodson.familybucks.accounts.service;

import com.justindodson.familybucks.accounts.model.entity.user.Family;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.repository.user.FamilyRepository;
import com.justindodson.familybucks.accounts.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FamilyServiceImpl implements FamilyService{
    private static final Logger LOGGER = LoggerFactory.getLogger(FamilyServiceImpl.class);

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    @Autowired
    public FamilyServiceImpl(FamilyRepository familyRepository, UserRepository userRepository) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
    }

    public void createNewFamily(Family family) {
         familyRepository.save(family);
    }

    public void updateFamily(long familyId, long parentId) {
        Optional<Family> family = familyRepository.findById(familyId);
        Optional<User> parent = userRepository.findById(parentId);

        if(family.isPresent() && parent.isPresent() && parent.get() instanceof Parent) {
            Family found = family.get();
            found.familyMembers.add(parent.get());
            familyRepository.save(found);
        }
        else{
            LOGGER.error("ERROR ADDING PARENT " + parent.get().getUsername() + " TO FAMILY: " + family.get().getFamilyName());
        }
    }

    // returns all family members of the user and sorts the user by the parents and children and in alphabetical order
    @Override
    public List<User> getAllFamilyMembers(User user) {
        List<User> users = new ArrayList<>();
        if(null != user.getFamily()){
            Optional<Family> family = familyRepository.findById(user.getFamily().getId());
            if(family.isPresent()) {
                family.get().familyMembers.forEach(member ->{
                    users.add(member);
                });
                Collections.sort(users, User.SORT_BY_PERSON_TYPE);
                Collections.sort(users, User.SORT_BY_FIRST_NAME);
                return users;
            }
        }
        return null;
    }
}
