package com.school.management.Utils.Exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String Message) {
        super(Message);
    }
}
