package com.spring.vote.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.vote.model.Admin;
import com.spring.vote.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
