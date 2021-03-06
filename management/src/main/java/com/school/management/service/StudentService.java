package com.school.management.service;

import com.school.management.model.person.Teacher;
import com.school.management.utils.Constants;
import com.school.management.utils.DateTime;
import com.school.management.utils.exceptions.ResourceNotFoundException;
import com.school.management.dao.StudentRepo;
import com.school.management.model.person.Student;
import com.school.management.model.SClass;
import com.school.management.model.person.User;
import com.school.management.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService implements IStudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public Student register(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setCreatedAt(DateTime.getCurrentDateTime());
        return this.studentRepo.save(student);
    }

    @Override
    public CompletableFuture<List<Teacher>> getAll() {
        return null;
    }

    @Override
    public void update(int id, Student student) {
        Optional<User> savedUser = this.studentRepo.findById(id);

        if(savedUser.isEmpty())
            throw new ResourceNotFoundException(Constants.ResponseMessageConstants.RESOURCE_NOT_FOUND);

        student.setCreatedAt(savedUser.get().getCreatedAt());
        student.setUpdatedAt(DateTime.getCurrentDateTime());
        student.setId(savedUser.get().getId());
        this.studentRepo.save(student);
    }

    @Override
    public void delete(int id) {
        this.studentRepo.deleteById(id);
    }

    @Override
    public List<Student> getAllStudentsByClassId(int id) {
        SClass sClass = new SClass();
        sClass.setId(id);
        return this.studentRepo.getAllByStudentClass(sClass);
    }
}
