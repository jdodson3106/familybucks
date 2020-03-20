package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.auth.UserRepository;
import com.justindodson.familybucks.app.model.entity.user.Child;
import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ChildServiceImpl implements ChildService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ChildServiceImpl.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

    private final UserRepository childRepository;

    @Autowired
    public ChildServiceImpl(UserRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public Child addChildToFamily(Child child, Family family) {
        child.setFamily(family);

        family.familyMembers.forEach(member ->{
            if (member.getUserType().equalsIgnoreCase("parent")) {
                child.getParentSet().add((Parent)member);
                ((Parent) member).getChildren().add(child);
            }
        });

        return child;
    }

    @Override
    public Child createOrUpdateChild(Child child) {
        Optional<User> foundChild = childRepository.findByUsername(child.getUsername());

        if(foundChild.isPresent()) {
            Child updated = (Child) foundChild.get();
            updated.setParentSet(child.getParentSet());
            updated.setFamily(child.getFamily());
            updated.setFirstName(child.getFirstName());
            updated.setLastName(child.getLastName());
            updated.setUsername(child.getUsername());
            updated.setId(child.getId());
            updated.setPassword1(child.getPassword1());
            updated.setPassword2(child.getPassword2());
            childRepository.save(updated);
            return updated;
        }
        else{
            try{
                if(child.getPassword1().equals(child.getPassword2())) {
                    String encodedPassword = encoder.encode(child.getPassword1());
                    child.setPassword1(encodedPassword);
                    child.setPassword2(encodedPassword);
                    childRepository.save(child);
                    return child;
                }
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
                LOGGER.error("Argument Exception", e);
            }
        }
        return null;
    }
}
