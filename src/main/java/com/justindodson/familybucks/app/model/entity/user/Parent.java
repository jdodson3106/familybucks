package com.justindodson.familybucks.app.model.entity.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("parent")
public class Parent extends Person{

    @ManyToMany
    @JoinTable(
            name = "parent_children",
            joinColumns = @JoinColumn(name="parent_id"),
            inverseJoinColumns = @JoinColumn(name="child_id")
    )
    private Set<Child> children = new HashSet<>();


}
