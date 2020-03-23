package com.justindodson.familybucks.accounts.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FamilyBucksUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public FamilyBucksUserDetailsService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).get();
        if(null == user) {
            throw new UsernameNotFoundException("Cannot find username: " + username);
        }

        return new UserPrincipal(user);
    }
}
