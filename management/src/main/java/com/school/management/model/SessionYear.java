package com.school.management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class SessionYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int startingYear;
    private int endYear;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_year_id")
    private List<SClass> classes;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getStartingYear() {
        return startingYear;
    }

    public void setStartingYear(int startingYear) {
        this.startingYear = startingYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public List<SClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SClass> classes) {
        this.classes = classes;
    }
}
