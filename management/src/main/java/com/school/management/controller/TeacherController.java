package com.school.management.controller;

import com.school.management.Utils.Exceptions.EmailAlreadyExistsException;
import com.school.management.Utils.Response.ResponseHandler;
import com.school.management.dao.TeacherRepo;
import com.school.management.model.Person.Teacher;
import com.school.management.model.Person.User;
import com.school.management.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @PostMapping("/teacher/add")
    public ResponseEntity<Object> addTeacher(@RequestBody Teacher teacher)
    {
        try
        {
            Teacher addedTeacher = this.teacherService.addTeacher(teacher);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, "Teacher added successfully", addedTeacher);
        }
        catch (DataIntegrityViolationException violationException)
        {
            logger.error("Email already Exists", violationException.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, "Email Already exists", null);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

    @GetMapping("teacher")
    public ResponseEntity<Object> getAllTeachers()
    {
        try
        {
            List<Teacher> teachers = this.teacherService.getAllTeacher();
            return ResponseHandler.generateResponse(HttpStatus.OK, "success", teachers);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

//    @PutMapping("teacher/{id}")
//    public ResponseEntity<Object> updateTeacher(@PathVariable (value = "id") long id, @RequestBody User user)
//    {
//
//    }
}
