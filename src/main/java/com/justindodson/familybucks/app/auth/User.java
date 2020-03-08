package com.justindodson.familybucks.app.auth;

import com.justindodson.familybucks.app.model.entity.BaseEntity;
import com.justindodson.familybucks.app.model.entity.user.Family;
import org.hibernate.validator.constraints.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
public abstract class User extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name="password1", nullable = false)
    private String password1;

    @Column(name="password2", nullable = false)
    private String password2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "family_id", nullable = true)
    private Family family;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}