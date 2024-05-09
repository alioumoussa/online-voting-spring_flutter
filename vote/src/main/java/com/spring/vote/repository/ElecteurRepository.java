package com.spring.vote.repository;

import com.spring.vote.model.Electeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElecteurRepository extends JpaRepository<Electeur, Long> {
    Electeur findOneByNumeroIdentification(String numeroIdentification);
}
