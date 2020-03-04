package com.justindodson.familybucks.app.model.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("child")
public class Child extends Person {

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parentSet = new HashSet<>();
}
