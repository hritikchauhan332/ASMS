package com.school.management.controller;

import com.school.management.utils.exceptions.ResourceAlreadyExistsException;
import com.school.management.utils.response.ResponseHandler;
import com.school.management.model.SessionYear;
import com.school.management.service.interfaces.ISessionYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionYearController {

    @Autowired
    ISessionYearService sessionYearService;

    @PostMapping("/add")
    public ResponseEntity<Object> addSessionYear(@RequestBody SessionYear sessionYear)
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

    @GetMapping("/all")
    public ResponseEntity<Object> getAllSessionYear()
    {
        try {
            List<SessionYear> sessionYearList = this.sessionYearService.getAllSessions();
            return ResponseHandler.generateResponse(HttpStatus.OK, "success", sessionYearList);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
