package com.school.management.helper;

import com.school.management.model.person.Teacher;
import com.school.management.model.person.User;

import java.util.ArrayList;
import java.util.List;

public class MockHelper {

    public static final String RESOURCE_NOT_FOUND_EXCEPTION_MESSAGE = "Resource not found";

    public User getFakeUserData()
    {
        User user = new User();

        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("Teacher");
        user.setEmail("testTeacher@test.com");

        return user;
    }

    public List<User> getFakeUsersList()
    {
        List<User> users = new ArrayList<>();

        users.add(getFakeUserData());
        users.add(getFakeUserData());

        return users;
    }

    public Teacher getFakeTeacherData()
    {
        Teacher teacher = new Teacher();

        teacher.setId(1);
        teacher.setFirstName("Test");
        teacher.setLastName("Teacher");
        teacher.setEmail("testTeacher@test.com");
        teacher.setPassword("fakePassword");

        return teacher;
    }

    public List<Teacher> getFakeTeachersList()
    {
        List<Teacher> teachers = new ArrayList<>();

        teachers.add(getFakeTeacherData());
        teachers.add(getFakeTeacherData());

        return teachers;
    }

    public Teacher getFakeTeacherToUpdateData()
    {
        Teacher teacher = getFakeTeacherData();
        teacher.setFirstName("New Fake Teacher");

        return teacher;
    }
}
