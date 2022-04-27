package com.school.management.service;

import com.school.management.Utils.Constants;
import com.school.management.Utils.Exceptions.ResourceAlreadyExistsException;
import com.school.management.dao.SessionYearRepo;
import com.school.management.model.SessionYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class SessionYearService {
    @Autowired
    SessionYearRepo sessionYearRepo;

    public void add(SessionYear sessionYear)
    {
        SessionYear storedSessionYear = this.sessionYearRepo.findByStartingYearAndEndYear(sessionYear.getStartingYear(), sessionYear.getEndYear());
        if(storedSessionYear != null)
            throw new ResourceAlreadyExistsException(MessageFormat.format(Constants.ResourceAlreadyExistExceptionConstants.RESOURCE_ALREADY_EXISTS_MESSAGE, "Session"));

        this.sessionYearRepo.save(sessionYear);
    }
}
