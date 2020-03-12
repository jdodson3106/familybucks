package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.model.entity.user.Family;

public interface FamilyMemberService {
    void addUserToFamily(User user, Family family);

}
