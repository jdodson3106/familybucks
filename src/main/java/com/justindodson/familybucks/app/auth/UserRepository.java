package com.justindodson.familybucks.app.auth;

import com.justindodson.familybucks.app.model.entity.user.Family;
import com.justindodson.familybucks.app.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByFamily(Family family);
    Optional<User> findByUsername(String username);
}
