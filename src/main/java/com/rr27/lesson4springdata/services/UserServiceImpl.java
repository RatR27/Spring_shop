package com.rr27.lesson4springdata.services;

import com.rr27.lesson4springdata.entities.Role;
import com.rr27.lesson4springdata.entities.User;
import com.rr27.lesson4springdata.repositories.RoleRepository;
import com.rr27.lesson4springdata.repositories.UserRepository;
import com.rr27.lesson4springdata.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByPhone(String phone) {
        return userRepository.findOneByPhone(phone);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findOneByPhone(phone);
        if (user == null){
            throw new UsernameNotFoundException("Неправильный логин или пароль");
        }
        //преобразуем нашего пользователя к виду, который понимает SpringSecurity (имя, пароль, роль)
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    //из наших ролей пользователя получаем SimpleGrantedAuthority понятный для Security загружая их туда
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public boolean isUserExist(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    @Transactional
    public User save(SystemUser systemUser){
        User user = new User();

        //еще раз проверим, что пользователя с таким именем еще нет в базе
        //хотя Исключение должно было вылететь еще раньше и сюда бы мы не дошли по хорошему
        if(findByPhone(systemUser.getPhone()) != null){
            throw new RuntimeException("Пользователь с таким номером телефона  " + systemUser.getPhone() +  " уже есть!");
        }

        user.setPhone(systemUser.getPhone());
        if (systemUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        }
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }
}
