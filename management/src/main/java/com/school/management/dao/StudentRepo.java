package com.school.management.dao;

import com.school.management.model.person.Student;
import com.school.management.model.SClass;

import java.util.List;

public interface StudentRepo extends UserRepo{

    public List<Student> getAllByStudentClass(SClass sClass);
}
