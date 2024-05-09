package com.spring.vote.configuration;


import jakarta.servlet.http.HttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.vote.model.Electeur;
import com.spring.vote.services.ExcelImportService;

@Configuration
public class SecurityConfiguration  {
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       return  http
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
               .build();

   }

    @Bean
    public ExcelImportService<Electeur> excelImportService() {
        return new ExcelImportService<>(Electeur.class); 
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
}
