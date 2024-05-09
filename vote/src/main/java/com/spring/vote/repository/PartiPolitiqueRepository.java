package com.spring.vote.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.vote.model.PartiPolitique;


public interface PartiPolitiqueRepository extends JpaRepository<PartiPolitique, Long> {
    
}
