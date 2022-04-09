package com.school.management.dao;

import com.school.management.model.Person.User;

import java.util.List;

public interface TeacherRepo extends UserRepo{

    List<User> findAllByRole(String role);
}
