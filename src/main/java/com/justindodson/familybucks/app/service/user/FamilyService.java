package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.model.repository.user.FamilyRepository;
import com.justindodson.familybucks.app.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private UserRepository userRepository;

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
    }

}
