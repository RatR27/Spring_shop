package com.rr27.lesson4springdata.repositories;

import com.rr27.lesson4springdata.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByPhone(String phone);
    boolean existByPhone(String phone);
}
