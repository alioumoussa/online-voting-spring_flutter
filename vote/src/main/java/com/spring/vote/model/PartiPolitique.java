package com.spring.vote.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "partis_politiques")
public class PartiPolitique {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomParti;
    private String description;
    private String programme;
    private String photoURL;
    private LocalDate dateFondation;

    public PartiPolitique(Long id, String nom, String description, String programme, String photoURL,
            LocalDate dateFondation) {
        this.id = id;
        this.nomParti = nom;
        this.description = description;
        this.programme = programme;
        this.photoURL = photoURL;
        this.dateFondation = dateFondation;
    }

    public PartiPolitique() {
    }

    public PartiPolitique(String nom, String description, String programme, String photoURL, LocalDate dateFondation) {
        this.nomParti = nom;
        this.description = description;
        this.programme = programme;
        this.photoURL = photoURL;
        this.dateFondation = dateFondation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nomParti;
    }

    public void setNom(String nom) {
        this.nomParti = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public LocalDate getDateFondation() {
        return dateFondation;
    }

    public void setDateFondation(LocalDate dateFondation) {
        this.dateFondation = dateFondation;
    }
   
  

}
