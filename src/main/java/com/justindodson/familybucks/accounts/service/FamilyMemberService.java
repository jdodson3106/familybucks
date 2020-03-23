package com.justindodson.familybucks.accounts.service;

import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.entity.user.Family;

public interface FamilyMemberService {
    void addUserToFamily(User user, Family family);

}
