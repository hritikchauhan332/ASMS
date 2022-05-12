package com.school.management.dao;

import com.school.management.model.SClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SClassRepo extends JpaRepository<SClass, Integer> {
    public SClass findByClassNameAndSessionYearId(String className, long sessionYearId);

    public List<SClass> findBySessionYearId(long sessionId);
}
