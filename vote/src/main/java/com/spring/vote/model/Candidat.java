package com.spring.vote.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "candidats")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String photoUrl;
    private LocalDate dateNaissance; 

    @ManyToOne
    @JoinColumn(name = "parti_politique_id")
    private PartiPolitique partiPolitique;

    public Candidat() {
    }


// Getters and setters

    public Candidat(Long id, String nom, String photoUrl, LocalDate dateNaissance,
            PartiPolitique partiPolitique) {
        this.id = id;
        this.nom = nom;
        this.photoUrl = photoUrl;
        this.dateNaissance = dateNaissance;
        this.partiPolitique = partiPolitique;
    }


    public Candidat(String nom, String photoUrl, LocalDate dateNaissance,
            PartiPolitique partiPolitique) {
        this.nom = nom;
        this.photoUrl = photoUrl;
        this.dateNaissance = dateNaissance;
        this.partiPolitique = partiPolitique;
    }


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


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public PartiPolitique getPartiPolitique() {
        return partiPolitique;
    }

    public void setPartiPolitique(PartiPolitique partiPolitique) {
        this.partiPolitique = partiPolitique;
    }
    
}
