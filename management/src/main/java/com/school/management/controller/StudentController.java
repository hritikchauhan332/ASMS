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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentService studentService;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/add")
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
}
