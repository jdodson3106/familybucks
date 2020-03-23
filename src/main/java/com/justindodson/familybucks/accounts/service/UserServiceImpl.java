package com.justindodson.familybucks.accounts.service;

import com.justindodson.familybucks.accounts.auth.User;
import com.justindodson.familybucks.accounts.auth.UserRepository;
import com.justindodson.familybucks.accounts.model.entity.user.Child;
import com.justindodson.familybucks.accounts.model.entity.user.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()) {
            User found = user.get();
            return found;
        }
        return null;
    }

    @Override
    public Parent getParentByUserType(String userType, long id) {
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent() && user.get().getUserType().equalsIgnoreCase("parent")) {
            return (Parent) user.get();
        }
        return null;
    }

    @Override
    public Child getChildByUserType(String userType, long id) {
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent() && user.get().getUserType().equalsIgnoreCase("child")) {
            return (Child) user.get();
        }
        return null;
    }
}
