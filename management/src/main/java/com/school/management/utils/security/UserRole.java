package com.school.management.utils.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.school.management.utils.security.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(
            TEACHER_ADD,
            TEACHER_DELETE,
            TEACHER_READ,
            TEACHER_UPDATE,
            STUDENT_ADD,
            STUDENT_DELETE,
            STUDENT_READ,
            STUDENT_UPDATE,
            SCLASS_ADD,
            SCLASS_DELETE,
            SCLASS_READ,
            SCLASS_UPDATE,
            SUBJECT_READ,
            SUBJECT_ADD,
            SUBJECT_DELETE,
            SUBJECT_UPDATE
    )),
    TEACHER(Sets.newHashSet(
            TEACHER_UPDATE,
            STUDENT_ADD,
            STUDENT_DELETE,
            STUDENT_READ,
            STUDENT_UPDATE,
            SUBJECT_READ,
            SUBJECT_ADD,
            SUBJECT_DELETE,
            SUBJECT_UPDATE
    )),
    STUDENT(Sets.newHashSet());

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) { this.permissions = permissions; }

    public Set<UserPermission> getPermissions() { return permissions; }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

    public static UserRole getRoleEnum(String role)
    {
        if(role.equalsIgnoreCase(ADMIN.toString()))
            return ADMIN;
        if(role.equalsIgnoreCase(TEACHER.toString()))
            return TEACHER;
        if(role.equalsIgnoreCase(STUDENT.toString()))
            return STUDENT;

        return null;
    }
}
