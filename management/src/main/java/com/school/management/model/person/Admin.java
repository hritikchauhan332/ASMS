package com.school.management.model.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends User {

}
