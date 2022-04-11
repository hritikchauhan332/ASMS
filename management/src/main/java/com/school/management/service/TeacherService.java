package com.school.management.service;

import com.school.management.Utils.Exceptions.ResourceNotFoundException;
import com.school.management.dao.TeacherRepo;
import com.school.management.model.Person.Teacher;
import com.school.management.model.Person.User;
import com.school.management.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    public Teacher add(Teacher teacher) { return this.teacherRepo.save(teacher); }

    public List<Teacher> getAllTeacher()
    {
        List<User> objectList = this.teacherRepo.findAllByRole(Role.TEACHER.toString());
        List<Teacher> teachers = new ArrayList<>();

        for(User user : objectList)
            teachers.add((Teacher) user);

        return teachers;
    }

    public void update(long id, Teacher teacher)
    {
        Optional<User> retrievedTeacher = this.teacherRepo.findById((int) id);
        if(retrievedTeacher == null)
            throw new ResourceNotFoundException("Unable to find Resource with specified Id");

        teacher.setId(retrievedTeacher.get().getId());
        this.teacherRepo.save(teacher);
    }

    public void delete(long id)
    {
        Optional<User> retrievedTeacher = this.teacherRepo.findById((int) id);
        if(retrievedTeacher == null)
            throw new ResourceNotFoundException("Unable to find Resource with specified Id");
        this.teacherRepo.deleteById((int)id);
    }
}
