package com.school.management.service;

import com.school.management.dao.UserRepo;
import com.school.management.model.person.Admin;
import com.school.management.service.interfaces.IAdminService;
import com.school.management.utils.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setCreatedAt(DateTime.getCurrentDateTime());
        this.userRepo.save(admin);
        return null;
    }

    @Override
    public List<Admin> getAll() { return null; }

    @Override
    public void update(int id, Admin person) {throw new UnsupportedOperationException();}

    @Override
    public void delete(int id) {throw new UnsupportedOperationException();}
}
