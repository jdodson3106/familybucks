package com.justindodson.familybucks.app.service.user;

import com.justindodson.familybucks.app.model.entity.user.Child;
import com.justindodson.familybucks.app.model.entity.user.Family;

public interface ChildService {
    Child addChildToFamily(Child child, Family family);
    Child createOrUpdateChild(Child child);
}
