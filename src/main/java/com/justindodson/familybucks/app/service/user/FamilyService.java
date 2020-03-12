package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.auth.User;
import com.justindodson.familybucks.app.model.entity.user.Family;

import java.util.List;

public interface FamilyService {
    void createNewFamily(Family family);
    void updateFamily(long familyId, long parentId);
    List<User> getAllFamilyMembers(User user);
}
