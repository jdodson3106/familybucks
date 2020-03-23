package com.justindodson.familybucks.accounts.auth;

import com.justindodson.familybucks.accounts.model.entity.BaseEntity;
import com.justindodson.familybucks.accounts.model.entity.user.Family;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Comparator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
@DiscriminatorValue(value = "user")
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

    public User() {
    }

    public User(String username, String firstName, String lastName, String password1, String password2, Family family) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password1 = password1;
        this.password2 = password2;
        this.family = family;
    }

    @Transient
    public String getUserType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public static Comparator<User> SORT_BY_PERSON_TYPE = new Comparator<User> () {

        @Override
        public int compare(User one, User two) {
            return two.getUserType().compareTo(two.getUserType());
        }
    };

    public static Comparator<User> SORT_BY_FIRST_NAME = new Comparator<User>() {
        @Override
        public int compare(User one, User two) {
            return one.getFirstName().compareTo(two.getFirstName());
        }
    };

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
