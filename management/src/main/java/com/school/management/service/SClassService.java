package com.school.management.service;

import com.school.management.Utils.Constants;
import com.school.management.Utils.Exceptions.ResourceAlreadyExistsException;
import com.school.management.dao.SClassRepo;
import com.school.management.model.SClass;
import com.school.management.service.interfaces.ISClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class SClassService implements ISClassService {

    @Autowired
    SClassRepo sClassRepo;

    public void addSClass(SClass sClass)
    {
        SClass storedSClass = this.sClassRepo.findByClassNameAndSessionYearId(sClass.getClassName(), sClass.getSessionYear().getId());
        if(storedSClass != null)
            throw new ResourceAlreadyExistsException(MessageFormat.format(Constants.ResourceAlreadyExistExceptionConstants.RESOURCE_ALREADY_EXISTS_MESSAGE, "Class"));

        this.sClassRepo.save(sClass);
    }

    public List<SClass> getClassesBySessionId(long sessionId) { return this.sClassRepo.findBySessionYearId(sessionId); }
}
