package com.school.management.dao;

import com.school.management.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
