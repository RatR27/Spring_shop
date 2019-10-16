package com.rr27.lesson4springdata.services;

import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.utils.SystemUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Данный сервис предназначен для вытаскивания инфы о пользователе по id
 */
public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    User save(SystemUser systemUser);
}
