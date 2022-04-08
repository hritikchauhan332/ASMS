package com.school.management.model;

import com.school.management.model.Person.Student;

import javax.persistence.*;
import java.util.List;

@Entity
public class SClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String className;

    @ManyToOne()
    private SessionYear sessionYear;

    @OneToMany()
    List<Student> students;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SessionYear getSessionYear() {
        return sessionYear;
    }

    public void setSessionYear(SessionYear sessionYear) {
        this.sessionYear = sessionYear;
    }
}
