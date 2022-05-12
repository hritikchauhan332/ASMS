package com.school.management.controller;

import com.school.management.Utils.Exceptions.ResourceAlreadyExistsException;
import com.school.management.Utils.Response.ResponseHandler;
import com.school.management.model.SClass;
import com.school.management.service.SClassService;
import com.school.management.service.interfaces.ISClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class SClassController {

    @Autowired
    ISClassService sClassService;

    @PostMapping("/add")
    ResponseEntity<Object> addSCLass(@RequestBody SClass sClass)
    {
        try {
            this.sClassService.addSClass(sClass);
            return ResponseHandler.generateResponse(HttpStatus.OK, "success", null);
        } catch (ResourceAlreadyExistsException ex) {
            return ResponseHandler.generateResponse(HttpStatus.CONFLICT, ex.getMessage(), null);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

    @GetMapping("/{sessionId}/all")
    ResponseEntity<Object> getClassBySessionId(@PathVariable (value = "sessionId") long sessionId)
    {
        try {
            List<SClass> classList = this.sClassService.getClassesBySessionId(sessionId);
            return ResponseHandler.generateResponse(HttpStatus.OK, "success", classList);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
