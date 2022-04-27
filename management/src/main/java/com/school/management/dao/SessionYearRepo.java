package com.school.management.dao;

import com.school.management.model.SessionYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionYearRepo extends JpaRepository<SessionYear, Integer> {
    public SessionYear findByStartingYearAndEndYear(int startYear, int endYear);
}
