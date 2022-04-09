package com.school.management.controller;

import com.school.management.Utils.Constants;
import com.school.management.Utils.Exceptions.EmailAlreadyExistsException;
import com.school.management.Utils.Response.ResponseHandler;
import com.school.management.model.Person.Admin;
import com.school.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/admin/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody Admin admin)
    {
        try
        {
            Admin registeredAdmin = this.userService.registerAdmin(admin);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, Constants.AdminConstants.ADMIN_REGISTERED_SUCCESSFULLY, registeredAdmin);
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

//    @PostMapping("/admin/login")
//    public Optional<Admin> loginAdmin(@RequestBody Admin admin)
//    {
//        //return this.adminService.loginAdmin(admin)
//    }
}
