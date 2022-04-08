package com.school.management.Utils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.MULTI_STATUS)
public class EmailAlreadyExistsException extends RuntimeException{
}
