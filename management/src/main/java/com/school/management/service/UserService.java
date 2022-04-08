package com.school.management.service;

import com.school.management.Utils.Exceptions.EmailAlreadyExistsException;
import com.school.management.dao.UserRepo;
import com.school.management.model.Person.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public Admin registerAdmin(Admin admin)
    {
        try
        {
            this.userRepo.save(admin);
            return null;
        }
        catch (DataIntegrityViolationException violationException)
        {
            violationException.printStackTrace();
            throw new EmailAlreadyExistsException();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
