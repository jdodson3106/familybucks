package com.justindodson.familybucks.accounts.service;

import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.model.entity.user.Child;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;

public interface UserService {
    User getUserByUsername(String username);
    Parent getParentByUserType(String userType, long id);
    Child getChildByUserType(String userType, long id);

}
