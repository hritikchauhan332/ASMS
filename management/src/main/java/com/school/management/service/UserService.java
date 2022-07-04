package com.school.management.service;

import com.school.management.dao.UserRepo;
import com.school.management.model.person.User;
import com.school.management.model.person.UserLogin;
import com.school.management.utils.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserLogin userLogin = new UserLogin();
        User user = userRepo.findByEmail(email);
        if(user == null)
            return userLogin;

        userLogin.setUserEmail(user.getEmail());
        userLogin.setPassword(user.getPassword());
        userLogin.setEnabled(true);
        UserRole role = UserRole.getRoleEnum(user.getRole());
        userLogin.setRole(user.getRole());
        userLogin.setGrantedAuthorities(role.getGrantedAuthorities());
        return userLogin;
    }
}
