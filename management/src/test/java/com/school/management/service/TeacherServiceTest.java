package com.school.management.service;

import com.school.management.dao.TeacherRepo;
import com.school.management.helper.MockHelper;
import com.school.management.model.Role;
import com.school.management.model.person.Teacher;
import com.school.management.model.person.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TeacherServiceTest {

    @Mock
    private TeacherRepo teacherRepo;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private TeacherService teacherService;

    private static MockHelper helper;

    @BeforeAll
    private static void init() {
        helper = new MockHelper();
    }

    @Test
    public void getAllTeacherTestShouldReturnTeacherList() {
        List<Teacher> teachers = helper.getFakeTeachersList();
        when(teacherRepo.findAllByRole(Role.TEACHER.toString())).thenReturn(teachers);

        List<Teacher> savedTeachers = teacherService.getAll();
        verify(teacherRepo, atLeast(1)).findAllByRole(anyString());
        assert savedTeachers.size() == teachers.size();
    }

    @Test
    public void GivenTeacherDataTeacherShouldBeSavedSuccessfully()
    {
        Teacher fakeTeacher = helper.getFakeTeacherData();
        when(teacherRepo.save(fakeTeacher)).thenReturn(fakeTeacher);

        Teacher savedTeacher = teacherService.register(fakeTeacher);
        verify(passwordEncoder, atLeast(1)).encode(anyString());
    }

    @Test
    public void GivenTeacherDataTeacherPresentInDatabaseShouldUpdateSuccessfully()
    {
        User teacher = (User) helper.getFakeTeacherData();
        Teacher teacherToUpdate = helper.getFakeTeacherToUpdateData();
        when(teacherRepo.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(teacher));

        teacherService.update(teacherToUpdate.getId(), teacherToUpdate);

        verify(teacherRepo, atLeastOnce()).findById(anyInt());
        verify(teacherRepo, atLeastOnce()).save(any());
    }

    @Test
    public void GivenTeacherDataTeacherNotPresentInDatabaseShouldThrowException()
    {
        Teacher teacherToUpdate = helper.getFakeTeacherToUpdateData();

        when(teacherRepo.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        try
        {
            teacherService.update(teacherToUpdate.getId(), teacherToUpdate);
        }
        catch (Exception ex)
        {
            assert ex.getMessage().equals("Unable to find Resource with specified Id");
        }
        verify(teacherRepo, atLeastOnce()).findById(anyInt());
        verify(teacherRepo, times(0)).save(any());
    }

    @Test
    public void GivenTeacherIdTeacherNotPresentInDatanaseShouldThrowException()
    {
        when(teacherRepo.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        try
        {
            teacherService.delete(anyInt());
        }
        catch (Exception ex)
        {
            assert ex.getMessage().equals("Unable to find Resource with specified Id");
        }

        verify(teacherRepo, times(1)).findById(anyInt());
        verify(teacherRepo, times(0)).deleteById(anyInt());
    }

    @Test
    public void GivenTeacherIdTeacherDeletedSuccessfully()
    {
        when(teacherRepo.findById(anyInt())).thenReturn(Optional.ofNullable(helper.getFakeTeacherData()));
        teacherService.delete(anyInt());

        verify(teacherRepo, times(1)).findById(anyInt());
        verify(teacherRepo, times(1)).deleteById(anyInt());
    }
}
