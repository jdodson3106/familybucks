package com.justindodson.familybucks.app.model.entity.user;

import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.model.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Family extends BaseEntity {

    @Column(name="family_name", unique = true)
    private String familyName;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    public Set<User> familyMembers = new HashSet<>();

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
