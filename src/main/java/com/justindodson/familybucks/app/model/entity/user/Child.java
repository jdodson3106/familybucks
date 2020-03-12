package com.justindodson.familybucks.app.model.entity.user;

import com.justindodson.familybucks.app.auth.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("child")
public class Child extends User {

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parentSet = new HashSet<>();

    public Child() {
    }

    public Child(String username, String firstName, String lastName, String password1, String password2, Family family) {
        super(username, firstName, lastName, password1, password2, family);
    }
    

    public Set<Parent> getParentSet() {
        return parentSet;
    }

    public void setParentSet(Set<Parent> parentSet) {
        this.parentSet = parentSet;
    }
}
