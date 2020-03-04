package com.justindodson.familybucks.app.service;

import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;
import com.justindodson.familybucks.app.model.entity.user.Person;
import com.justindodson.familybucks.app.model.repository.user.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ParentService {

    private static final Logger logger = LoggerFactory.getLogger(ParentService.class);

    private final PersonRepository parentRepository;

    @Autowired
    public ParentService(PersonRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    // saves or updates given parent object. if the parent parameter is null it will throw and exception.
    public void createOrUpdateParent(Parent parent) {
        try{
            parentRepository.save(parent);
        } catch (IllegalArgumentException ae) {
            ae.printStackTrace();
            logger.info("Parent entity with id: " + parent.getId() +" not found.");
        }
    }

    // return list of parent objects
    public List<Parent> getAllParents() {
        List<Parent> parents = new ArrayList<>();
        Iterator<Parent> parentIterator = (Iterator<Parent>) parentRepository.findAll();
        parentIterator.forEachRemaining(parent -> {
            parents.add(parent);
        });

        return parents;
    }

    // return the parent if it exists else return null
    public Parent getParentById(long id) {
        Optional<Person> foundParent = parentRepository.findById(id);
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
}
