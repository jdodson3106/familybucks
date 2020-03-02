package com.justindodson.familybucks.app.model.repository.user;

import com.justindodson.familybucks.app.model.entity.user.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends CrudRepository<Long, Parent> {
}
