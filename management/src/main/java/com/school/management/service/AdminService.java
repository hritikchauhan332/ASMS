package com.school.management.service;

import com.school.management.Utils.Exceptions.EmailAlreadyExistsException;
import com.school.management.dao.UserRepo;
import com.school.management.model.Person.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Admin registerAdmin(Admin admin)
    {
        try
        {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            this.userRepo.save(admin);
            return null;
        }
        catch (DataIntegrityViolationException violationException)
        {
            throw new EmailAlreadyExistsException();
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
