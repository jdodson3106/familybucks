package com.justindodson.familybucks.accounts.model.repository.user;

import com.justindodson.familybucks.accounts.model.entity.user.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {
}
