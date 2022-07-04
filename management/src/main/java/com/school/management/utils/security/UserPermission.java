package com.school.management.utils.security;

public enum UserPermission {
    // Teacher
    TEACHER_ADD("teacher:add"),
    TEACHER_DELETE("teacher:delete"),
    TEACHER_UPDATE("teacher:update"),
    TEACHER_READ("teacher:read"),
    //Student
    STUDENT_ADD("student:add"),
    STUDENT_DELETE("student:delete"),
    STUDENT_UPDATE("student:update"),
    STUDENT_READ("student:read"),
    //Class
    SCLASS_ADD("sclass:add"),
    SCLASS_DELETE("sclass:delete"),
    SCLASS_UPDATE("sclass:update"),
    SCLASS_READ("sclass:read"),
    //Subjects
    SUBJECT_ADD("subject:add"),
    SUBJECT_DELETE("subject:delete"),
    SUBJECT_UPDATE("subject:update"),
    SUBJECT_READ("subject:read");

    private final String permission;

    UserPermission(String permission) { this.permission = permission; }

    public String getPermission() { return permission; }
}
