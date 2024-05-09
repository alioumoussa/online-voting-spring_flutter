package com.spring.vote.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    private LocalDateTime dateVote; // Date et heure du vote

    // Getters and setters

    public Vote() {
    }

    public Vote(Long id, Candidat candidat, Election election, LocalDateTime dateVote) {
        this.id = id;
        this.candidat = candidat;
        this.election = election;
        this.dateVote = dateVote;
    }
    public Vote(Candidat candidat, Election election, LocalDateTime dateVote) {
        this.candidat = candidat;
        this.election = election;
        this.dateVote = dateVote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public LocalDateTime getDateVote() {
        return dateVote;
    }

    public void setDateVote(LocalDateTime dateVote) {
        this.dateVote = dateVote;
    }
}
