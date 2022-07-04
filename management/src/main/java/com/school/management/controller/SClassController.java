package com.school.management.controller;

import com.school.management.utils.Constants;
import com.school.management.utils.exceptions.ResourceAlreadyExistsException;
import com.school.management.utils.response.ResponseHandler;
import com.school.management.model.SClass;
import com.school.management.service.interfaces.ISClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/class")
public class SClassController {

    @Autowired
    ISClassService sClassService;

    Logger logger = LoggerFactory.getLogger(SClassController.class);

    @PostMapping("/add")
    ResponseEntity<Object> addSCLass(@RequestBody SClass sClass)
    {
        try {
            this.sClassService.addSClass(sClass);
            logger.info(MessageFormat.format(Constants.SClassControllerConstants.CLASS_ADDED_SUCCESSFULLY, sClass.getClassName()));
            return ResponseHandler.generateResponse(HttpStatus.OK, Constants.ResponseMessageConstants.SUCCESS, null);
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(MessageFormat.format(Constants.SClassControllerConstants.CLASS_ALREADY_EXISTS, sClass.getClassName()), ex);
            return ResponseHandler.generateResponse(HttpStatus.CONFLICT, ex.getMessage(), null);
        } catch (Exception ex) {
            logger.error(Constants.SClassControllerConstants.ADD_CLASS_FAIL, ex);
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

    @GetMapping("/{sessionId}/all")
    ResponseEntity<Object> getClassBySessionId(@PathVariable (value = "sessionId") int sessionId)
    {
        try {
            List<SClass> classList = this.sClassService.getClassesBySessionId(sessionId);
            return ResponseHandler.generateResponse(HttpStatus.OK, Constants.ResponseMessageConstants.SUCCESS, classList);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
