package com.school.management.utils;

public class Constants {

    private Constants(){}

    public static class AdminControllerConstants {
        private AdminControllerConstants(){}
        public static final String ADMIN_REGISTERED_SUCCESSFULLY = "Admin has been registered successfully";
    }

    public static class AuthenticationControllerConstants {
        private AuthenticationControllerConstants(){}

        public static final String USER_LOGGED_IN_SUCCESSFULLY = "User with email id {0} logged in  successfully";
        public static final String AUTHENTICATION_FAILED = "Authentication failed with exception: {0}";
        public static final String BAD_CREDENTIALS = "Bad Credentials";
    }

    public static class JwtTokenHelperContants {
        private JwtTokenHelperContants(){}

        public static final String APP_NAME = "ASMS";
        public static final String SECRET_KEY = "This is my secret key, a very secret key :)";
        public static final String AUTHORITIES = "authorities";
        public static final int EXPIRES_IN = 3600;
    }

    public static class ResourceAlreadyExistExceptionConstants {
        private ResourceAlreadyExistExceptionConstants(){}
        public static final String RESOURCE_ALREADY_EXISTS_MESSAGE = "{0} already exists";
    }

    public static class ResponseMessageConstants {
        private ResponseMessageConstants(){}
        public static final String SUCCESS = "success";
        public static final String EMAIL_ALREADY_EXISTS = "Email Already Exists";
        public static final String RESOURCE_NOT_FOUND = "Unable to find Student with specified Id";
    }

    public static class SClassControllerConstants {
        private SClassControllerConstants(){}
        public static final String CLASS_ADDED_SUCCESSFULLY = "successfully added class with name {0}";
        public static final String CLASS_ALREADY_EXISTS = "class with name {0} already exists";
        public static final String ADD_CLASS_FAIL = "Failed to add class with exception:";
    }

    public static class SpringFoxConfigConstants {
        private SpringFoxConfigConstants(){}
        public static final String TITLE = "ASMS REST API";
        public static final String DESCRIPTION = "All API'S for ASMS application";
        public static final String VERSION = "1";
        public static final String TERMS_OF_SERVICE_URL = "Terms of services";
        public static final String CONTACT_NAME = "hritikchauhan332@gmail.com";
        public static final String LICENSE = "License of API";
        public static final String LICENSE_URL = "API License URL";
    }
}
