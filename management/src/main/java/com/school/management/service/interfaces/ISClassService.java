package com.school.management.service.interfaces;

import com.school.management.model.SClass;

import java.util.List;

public interface ISClassService {
    public void addSClass(SClass sClass);

    public List<SClass> getClassesBySessionId(long id);
}
