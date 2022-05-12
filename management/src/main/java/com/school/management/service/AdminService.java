package com.school.management.service;

import com.school.management.Utils.Exceptions.EmailAlreadyExistsException;
import com.school.management.dao.UserRepo;
import com.school.management.model.Person.Admin;
import com.school.management.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Admin register(Admin admin)
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

    @Override
    public List<Admin> getAll() { return null; }

    @Override
    public void update(long id, Admin Person) {}

    @Override
    public void delete(long id) {}
}
