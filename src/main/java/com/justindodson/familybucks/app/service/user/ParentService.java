package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.model.entity.user.Parent;

import java.util.List;

public interface ParentService {
    Parent createOrUpdateParent(Parent parent);
    List<Parent> getAllParents();
    Parent getParentById(long id);
    List<Parent> findParentsByFamily(Family family);
    Parent getParentByUsername(String username);
}
