package com.spring.vote.repository;

import com.spring.vote.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    
    Admin findByEmail(String email);
}
