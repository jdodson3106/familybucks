package com.justindodson.familybucks.accounts.service;

import com.justindodson.familybucks.accounts.model.entity.user.Family;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;

import java.util.List;

public interface ParentService {
    Parent createOrUpdateParent(Parent parent);
    List<Parent> getAllParents();
    Parent getParentById(long id);
    List<Parent> findParentsByFamily(Family family);
    Parent getParentByUsername(String username);
}
