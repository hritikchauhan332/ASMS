package com.school.management.service;

import com.school.management.Utils.Constants;
import com.school.management.Utils.Exceptions.ResourceAlreadyExistsException;
import com.school.management.dao.SessionYearRepo;
import com.school.management.model.SessionYear;
import com.school.management.service.interfaces.ISessionYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class SessionYearService implements ISessionYearService {
    @Autowired
    SessionYearRepo sessionYearRepo;

    public void add(SessionYear sessionYear)
    {
        SessionYear storedSessionYear = this.sessionYearRepo.findByStartingYearAndEndYear(sessionYear.getStartingYear(), sessionYear.getEndYear());
        if(storedSessionYear != null)
            throw new ResourceAlreadyExistsException(MessageFormat.format(Constants.ResourceAlreadyExistExceptionConstants.RESOURCE_ALREADY_EXISTS_MESSAGE, "Session"));

        this.sessionYearRepo.save(sessionYear);
    }

    public List<SessionYear> getAllSessions()
    {
        List<SessionYear> sessionYearList = this.sessionYearRepo.findAll();
        return sessionYearList;
    }
}
