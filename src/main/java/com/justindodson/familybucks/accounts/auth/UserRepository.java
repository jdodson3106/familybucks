package com.justindodson.familybucks.accounts.auth;

import com.justindodson.familybucks.accounts.model.entity.user.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByFamily(Family family);
    Optional<User> findByUsername(String username);
}
