package com.justindodson.familybucks.accounts.service;

import com.justindodson.familybucks.accounts.model.entity.user.Child;
import com.justindodson.familybucks.accounts.model.entity.user.Family;

public interface ChildService {
    Child addChildToFamily(Child child, Family family);
    Child createOrUpdateChild(Child child);
}
