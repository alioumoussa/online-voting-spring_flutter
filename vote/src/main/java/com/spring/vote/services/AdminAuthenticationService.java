package com.spring.vote.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.vote.model.Admin;
import com.spring.vote.repository.AdminRepository;

// Service pour gérer l'authentification des administrateurs
@Service
public class AdminAuthenticationService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String email, String password) {
        // Récupérer l'administrateur à partir de l'email
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            return false; // L'administrateur n'existe pas
        }
        
        // Vérifier si le mot de passe correspond
        return passwordEncoder.matches(password, admin.getMotDePasse());
    }
}