package com.school.management.service.interfaces;

import com.school.management.model.SessionYear;

import java.util.List;

public interface ISessionYearService {
    public void add(SessionYear sessionYear);

    public List<SessionYear> getAllSessions();
}
