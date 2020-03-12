package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    private static final Logger logger = LoggerFactory.getLogger(ParentService.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

    private final UserRepository parentRepository;

    @Autowired
    public ParentService(UserRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    // saves or updates given parent object. if the parent parameter is null it will throw and exception.
    public Parent createOrUpdateParent(Parent parent) {
        Optional<User> foundParent = parentRepository.findByUsername(parent.getUsername());

        try{
            String password = encoder.encode(parent.getPassword1());
            parent.setPassword1(password);
            parent.setPassword2(password);
            return parentRepository.save(parent);

        } catch (IllegalArgumentException ae) {
            ae.printStackTrace();
            logger.info("Parent entity with id: " + parent.getId() +" not found.");
        }
        return null;
    }

    // return list of parent objects
    public List<Parent> getAllParents() {
        List<Parent> parents = new ArrayList<>();
        Iterable<User> people = parentRepository.findAll();

        people.forEach(person -> {
            if(person instanceof Parent){
                parents.add((Parent) person);
            }
        });

        return parents;
    }

    // return the parent if it exists else return null
    public Parent getParentById(long id) {
        Optional<User> foundParent = parentRepository.findById(id);
        if(foundParent.isPresent() && (foundParent.get() instanceof Parent)){
            return (Parent) foundParent.get();
        }
        return null;
    }

    // returns a list of parents in a family
    public List<Parent> findParentsByFamily(Family family) {
        Iterator<Parent> parentIterator = (Iterator<Parent>) parentRepository.findAllByFamily(family);
        List<Parent> parentList = new ArrayList<>();

        while(parentIterator.hasNext()) {
            Parent parent = parentIterator.next();
            parentList.add(parent);
        }

        return parentList;
    }

    public Parent getParentByUsername(String username) {
        Optional<User> foundParent = parentRepository.findByUsername(username);
        if(foundParent.isPresent() && (foundParent.get() instanceof Parent)) {
            return (Parent) foundParent.get();
        }
        return null;
    }
}
