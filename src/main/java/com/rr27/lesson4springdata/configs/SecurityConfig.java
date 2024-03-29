package com.rr27.lesson4springdata.configs;

import com.rr27.lesson4springdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //пример inMemoryAuthentication()
        //noop - пароль в явном виде, bcrypt - заскриптованный хэш
//        auth.inMemoryAuthentication()
//                .withUser(user.username("*").password("{noop}*").roles("*"))
//                .withUser(user.username("*").password("{bcrypt}*").roles("*"));

    }

    /** модуль настроек приложения
     * указываем url и следом нужное для этого полномочие
     * полномочия пишутся без префикса ROLE_
     * дальше идет форма настройки регистрации
     * при разлогинивании можно доп настроить: удаление Куки
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/admin/products/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/admin/users/**").hasRole("ADMIN")
                .antMatchers("/shop/order/**").authenticated()
                .antMatchers("/profile/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/shop")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //бин для аутентификации пользователя, который закинем в configure
    //раз используем dao считаем что наш пользователь живет в БД
    //можно еще сделать inMemoryAP - где логин-пароль хранится в списке в памяти и проверяется оттуда
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
