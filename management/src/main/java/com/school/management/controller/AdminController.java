package com.school.management.controller;

import com.school.management.utils.Constants;
import com.school.management.utils.exceptions.EmailAlreadyExistsException;
import com.school.management.utils.response.ResponseHandler;
import com.school.management.model.person.Admin;
import com.school.management.service.interfaces.IAdminService;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody Admin admin)
    {
        try
        {
            Admin registeredAdmin = this.adminService.register(admin);
            logger.info(Constants.AdminControllerConstants.ADMIN_REGISTERED_SUCCESSFULLY);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, Constants.ResponseMessageConstants.SUCCESS, registeredAdmin);
        }
        catch (EmailAlreadyExistsException emailAlreadyExistsException)
        {
            logger.error(Constants.ResponseMessageConstants.EMAIL_ALREADY_EXISTS, emailAlreadyExistsException.getMessage());
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, Constants.ResponseMessageConstants.EMAIL_ALREADY_EXISTS, null);
        }
        catch (Exception ex)
        {
            logger.error(Constants.ResponseMessageConstants.EMAIL_ALREADY_EXISTS, ex.getMessage());
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, Constants.ResponseMessageConstants.EMAIL_ALREADY_EXISTS, null);
        }
    }
}