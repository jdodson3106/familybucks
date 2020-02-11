package com.justindodson.familybucks.auth.data.repository;

import com.justindodson.familybucks.auth.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
