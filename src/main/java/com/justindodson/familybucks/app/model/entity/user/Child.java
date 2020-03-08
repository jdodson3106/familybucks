package com.justindodson.familybucks.app.model.entity.user;

import com.justindodson.familybucks.app.auth.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("child")
public class Child extends User {

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parentSet = new HashSet<>();
}
