package com.school.management.controller;

import com.school.management.Utils.Exceptions.ResourceAlreadyExistsException;
import com.school.management.Utils.Response.ResponseHandler;
import com.school.management.model.SessionYear;
import com.school.management.service.SessionYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionYearController {

    @Autowired
    SessionYearService sessionYearService;

    @PostMapping("/add")
    public ResponseEntity<?> addSessionYear(@RequestBody SessionYear sessionYear)
    {
        try {
            this.sessionYearService.add(sessionYear);
            return ResponseHandler.generateResponse(HttpStatus.OK, "Session added successfully", null);
        } catch (ResourceAlreadyExistsException ex) {
            return ResponseHandler.generateResponse(HttpStatus.CONFLICT, ex.getMessage(), null);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
