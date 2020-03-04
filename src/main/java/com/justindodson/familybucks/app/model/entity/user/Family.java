package com.justindodson.familybucks.app.model.entity.user;

import com.justindodson.familybucks.app.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Family extends BaseEntity {

    @Column(name="family_name")
    private String familyName;

    @OneToMany(mappedBy = "family")
    public Set<Person> familyMembers = new HashSet<>();

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
