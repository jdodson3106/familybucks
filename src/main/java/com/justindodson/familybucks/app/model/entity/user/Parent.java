package com.justindodson.familybucks.app.model.entity.user;

import com.justindodson.familybucks.app.auth.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("parent")
public class Parent extends User {

    @Column(name="email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "parent_children",
            joinColumns = @JoinColumn(name="parent_id"),
            inverseJoinColumns = @JoinColumn(name="child_id")
    )
    private Set<Child> children = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }
}
