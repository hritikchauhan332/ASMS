package com.school.management.controller;

import com.school.management.utils.Constants;
import com.school.management.utils.exceptions.EmailAlreadyExistsException;
import com.school.management.utils.response.ResponseHandler;
import com.school.management.model.person.Student;
import com.school.management.service.interfaces.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentService studentService;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('student:add')")
    public ResponseEntity<Object> addStudent(@RequestBody Student student)
    {
        try
        {
            Student registeredStudent= this.studentService.register(student);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, Constants.ResponseMessageConstants.SUCCESS, registeredStudent);
        }
        catch (EmailAlreadyExistsException emailAlreadyExistsException)
        {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, "Email Already Exists", null);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, "Email Already Exists", null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('student:update')")
    public ResponseEntity<Object> updateStudent(@PathVariable (value = "id") int id, @RequestBody Student student)
    {
        try
        {
            this.studentService.update(id, student);
            return ResponseHandler.generateResponse(HttpStatus.OK, Constants.ResponseMessageConstants.SUCCESS, null);
        }
        catch (com.school.management.utils.exceptions.ResourceNotFoundException ex)
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
