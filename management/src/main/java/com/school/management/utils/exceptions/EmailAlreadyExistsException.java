package com.school.management.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.MULTI_STATUS)
public class EmailAlreadyExistsException extends RuntimeException{
}
