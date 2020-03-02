package com.justindodson.familybucks.app.model.entity.user;

import com.justindodson.familybucks.app.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Family extends BaseEntity {

    @Column(name="family_name")
    private String familyName;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
