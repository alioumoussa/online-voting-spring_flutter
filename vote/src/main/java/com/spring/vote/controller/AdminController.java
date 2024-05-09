package com.spring.vote.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.vote.model.Admin;
import com.spring.vote.model.Candidat;
import com.spring.vote.model.Electeur;
import com.spring.vote.services.AdminAuthenticationService;
import com.spring.vote.services.AdminService;
import com.spring.vote.services.CandidatService;
import com.spring.vote.services.ElecteurService;

@Controller
@RequestMapping("/")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
        @Autowired
    private ElecteurService electeurService;

      @Autowired
    private CandidatService candidatService;

    

    @Autowired
    private PasswordEncoder passwordEncoder;
        @Autowired
    private AdminAuthenticationService adminAuthenticationService;

    // @GetMapping("/")
    // public String index(Model model){
    //     model.addAttribute("electeur", new Electeur());
    //     return "/pages/dashboard";
    // }
    
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("electeur", new Electeur());
        return "index";    }

    
   

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "register-admin";
    }

    @PostMapping("/register")
    public String registerAdmin(Admin admin) {
        // Encrypter le mot de passe avant de l'enregistrer dans la base de données
        admin.setMotDePasse(passwordEncoder.encode(admin.getMotDePasse()));
        adminService.saveAdmin(admin);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Afficher le formulaire de connexion (vue login.html)
    }
    @GetMapping("/pages/electeurs")
    public String showLogindashbord(Model model) {
        List<Electeur> electeurs = electeurService.getAllElecteurs();
        model.addAttribute("electeurs", electeurs);
        return "pages/dashboard"; // Afficher le formulaire de connexion (vue login.html)
    }

    @GetMapping("/pages/candidats")
    public String showCandidatdashbord(Model model) {
        List<Candidat> candidats = candidatService.getAllCandidats();
        model.addAttribute("candidats", candidats);
        return "pages/candidats"; // Afficher le formulaire de connexion (vue login.html)
    }



    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        // String email = credentials.get("email");
        // String password = credentials.get("password");
        System.out.println(password);

        // Vérifier les informations d'identification
        if (adminAuthenticationService.authenticate(email, password)) {
            // return ResponseEntity.ok("Authentication successful");
            
            return "redirect:/pages/electeurs";  // Si authentification ré
        } else {
            // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            // RequestAttributes.getAttribute("error", "Identifiants invalides");
            return "redirect:/login";
        }
    }

}