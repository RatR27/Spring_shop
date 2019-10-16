package com.rr27.lesson4springdata.utils;


import com.rr27.lesson4springdata.utils.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Класс обертка при регистрации
 * Здесь мы ограничиваем пользователя в том, что и как он может вводить в поля
 * Так как Hibernate валидатор работает по своей логике, то мы создаем свой вариант сравнения двух полей в utils
 */
@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SystemUser {

    @NotNull(message = "Поле не может быть пустое")
    @Size(min = 3, message = "Логин должен быть боьше 2 символов")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 4, message = "Слишком короткий")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 4, message = "Слишком короткий")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Size(min = 4, message = "is required")
    @Email
    private String email;

    @NotNull(message = "is required")
    @Size(min = 8, message = "is required")
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
