package com.justindodson.familybucks.app.model.repository.user;

import com.justindodson.familybucks.app.model.entity.user.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {
}
