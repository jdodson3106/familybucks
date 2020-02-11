package com.justindodson.familybucks.auth.data.repository;

import com.justindodson.familybucks.auth.data.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
