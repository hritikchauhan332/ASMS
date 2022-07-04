package com.school.management.model.person;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "teacher")
public class Teacher extends User {
    private int teachingExperienceInYears;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)

    public int getTeachingExperienceInYears() {
        return teachingExperienceInYears;
    }

    public void setTeachingExperienceInYears(int teachingExperienceInYears) {
        this.teachingExperienceInYears = teachingExperienceInYears;
    }
}
