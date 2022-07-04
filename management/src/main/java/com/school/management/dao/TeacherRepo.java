package com.school.management.dao;

import com.school.management.model.person.Teacher;
import com.school.management.model.person.User;

import java.util.List;

public interface TeacherRepo extends UserRepo{

    List<Teacher> findAllByRole(String role);
}
