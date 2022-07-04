package com.school.management.service;

import com.school.management.utils.DateTime;
import com.school.management.utils.exceptions.ResourceNotFoundException;
import com.school.management.dao.TeacherRepo;
import com.school.management.model.person.Teacher;
import com.school.management.model.person.User;
import com.school.management.model.Role;
import com.school.management.service.interfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Teacher register(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setCreatedAt(DateTime.getCurrentDateTime());
        return this.teacherRepo.save(teacher);
    }

    public List<Teacher> getAll() { return this.teacherRepo.findAllByRole(Role.TEACHER.toString()); }

    public void update(int id, Teacher teacher)
    {
        Optional<User> retrievedTeacher = this.teacherRepo.findById(id);
        if(retrievedTeacher.isEmpty())
            throw new ResourceNotFoundException("Unable to find Resource with specified Id");

        teacher.setId(retrievedTeacher.get().getId());
        teacher.setCreatedAt(retrievedTeacher.get().getCreatedAt());
        teacher.setUpdatedAt(DateTime.getCurrentDateTime());

        this.teacherRepo.save(teacher);
    }

    public void delete(int id)
    {
        Optional<User> retrievedTeacher = this.teacherRepo.findById(id);
        if(retrievedTeacher.isEmpty())
            throw new ResourceNotFoundException("Unable to find Resource with specified Id");
        this.teacherRepo.deleteById(id);
    }
}
