package com.school.management.service.interfaces;

import com.school.management.model.person.Student;

import java.util.List;

public interface IStudentService extends IPersonService<Student> {

    public List<Student> getAllStudentsByClassId(int id);
}
