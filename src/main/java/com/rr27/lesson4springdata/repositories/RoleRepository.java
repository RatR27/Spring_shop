package com.rr27.lesson4springdata.repositories;

import com.rr27.lesson4springdata.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String name);
}
