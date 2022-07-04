package com.school.management.model.person;

import com.school.management.model.SClass;
import com.school.management.model.SessionYear;
import com.school.management.model.Subject;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "student")
public class Student extends User {
    @ManyToOne()
    private SessionYear sessionYear;

    @ManyToOne()
    private SClass studentClass;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subject> subjects;

    public SessionYear getSessionYear() {
        return sessionYear;
    }

    public SClass getStudentClass() {
        return studentClass;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
