package com.justindodson.familybucks.accounts.service;

import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.entity.user.Family;

import java.util.List;

public interface FamilyService {
    void createNewFamily(Family family);
    void updateFamily(long familyId, long parentId);
    List<User> getAllFamilyMembers(User user);
}
