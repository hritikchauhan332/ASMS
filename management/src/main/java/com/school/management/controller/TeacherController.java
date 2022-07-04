package com.school.management.controller;

import com.school.management.utils.Constants;
import com.school.management.utils.exceptions.ResourceNotFoundException;
import com.school.management.utils.response.ResponseHandler;
import com.school.management.model.person.Teacher;
import com.school.management.service.interfaces.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    ITeacherService teacherService;

    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('teacher:add')")
    public ResponseEntity<Object> addTeacher(@RequestBody Teacher teacher)
    {
        try
        {
            Teacher addedTeacher = this.teacherService.register(teacher);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, Constants.ResponseMessageConstants.SUCCESS, addedTeacher);
        }
        catch (DataIntegrityViolationException violationException)
        {
            logger.error(Constants.ResponseMessageConstants.EMAIL_ALREADY_EXISTS, violationException.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, Constants.ResponseMessageConstants.EMAIL_ALREADY_EXISTS, null);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('teacher:read')")
    public ResponseEntity<Object> getAllTeachers()
    {
        try
        {
            CompletableFuture<List<Teacher>> teachersList = this.teacherService.getAll();
            List<Teacher> teachers = teachersList.get();
            return ResponseHandler.generateResponse(HttpStatus.OK, Constants.ResponseMessageConstants.SUCCESS, teachers);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex.getStackTrace());
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('teacher:update')")
    public ResponseEntity<Object> updateTeacher(@PathVariable (value = "id") int id, @RequestBody Teacher teacher)
    {
        try
        {
            this.teacherService.update(id, teacher);
            return ResponseHandler.generateResponse(HttpStatus.OK, Constants.ResponseMessageConstants.SUCCESS, null);
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

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('teacher:delete')")
    public ResponseEntity<Object> deleteTeacher(@PathVariable (value = "id") int id)
    {
        try
        {
            this.teacherService.delete(id);
            return ResponseHandler.generateResponse(HttpStatus.OK, Constants.ResponseMessageConstants.SUCCESS, null);
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
