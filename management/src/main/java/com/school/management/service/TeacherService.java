package com.school.management.service;

import com.school.management.dao.TeacherRepo;
import com.school.management.model.Person.Teacher;
import com.school.management.model.Person.User;
import com.school.management.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    public Teacher addTeacher(Teacher teacher)
    {
        return this.teacherRepo.save(teacher);
    }

    public List<Teacher> getAllTeacher()
    {
        List<User> objectList = this.teacherRepo.findAllByRole(Role.TEACHER.toString());
        List<Teacher> teachers = new ArrayList<>();

        for(User user : objectList)
            teachers.add((Teacher) user);

        return teachers;
    }
}
