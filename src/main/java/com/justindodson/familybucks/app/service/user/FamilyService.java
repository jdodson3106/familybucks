package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.model.repository.user.FamilyRepository;
import com.justindodson.familybucks.app.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FamilyService.class);

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    @Autowired
    public FamilyService(FamilyRepository familyRepository, UserRepository userRepository) {
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

}
