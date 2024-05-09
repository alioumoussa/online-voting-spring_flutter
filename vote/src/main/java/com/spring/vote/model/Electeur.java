package com.spring.vote.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "electeurs")
public class Electeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String numeroIdentification;
    private String numeroTelephone;
    private LocalDate dateNaissance; // Date de naissance de l'électeur

    
    private boolean voted = false;

    public Electeur() {
    }

    public Electeur(String nom, String prenom, String adresse, String numeroIdentification, String numeroTelephone, LocalDate dateNaissance, boolean voted) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroIdentification = numeroIdentification;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
        
        this.voted = voted;
    }

    public Electeur(Long id, String nom, String prenom, String adresse, String numeroIdentification, String numeroTelephone, LocalDate dateNaissance, boolean voted) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroIdentification = numeroIdentification;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
       
        this.voted = voted;

    }
// Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroIdentification() {
        return numeroIdentification;
    }

    public void setNumeroIdentification(String numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate localDate) {
        this.dateNaissance = localDate;
    }


    public boolean getVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
