package com.school.management.controller;

import com.school.management.Utils.Exceptions.ResourceNotFoundException;
import com.school.management.Utils.Response.ResponseHandler;
import com.school.management.model.Person.Teacher;
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
            Teacher addedTeacher = this.teacherService.add(teacher);
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

    @GetMapping("teacher/all")
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

    @PutMapping("teacher/update/{id}")
    public ResponseEntity<Object> updateTeacher(@PathVariable (value = "id") long id, @RequestBody Teacher teacher)
    {
        try
        {
            this.teacherService.update(id, teacher);
            return ResponseHandler.generateResponse(HttpStatus.OK, "Teacher Updated Successfully", null);
        }
        catch (ResourceNotFoundException ex)
        {
            logger.error(ex.getMessage());
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

    @DeleteMapping("teacher/delete/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable (value = "id") long id)
    {
        try
        {
            this.teacherService.delete(id);
            return ResponseHandler.generateResponse(HttpStatus.OK, "Teacher Deleted Successfully", null);
        }
        catch (ResourceNotFoundException ex)
        {
            logger.error(ex.getMessage());
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
