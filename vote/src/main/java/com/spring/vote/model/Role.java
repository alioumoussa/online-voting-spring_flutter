package com.spring.vote.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleName name;

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    // Getters and setters...
}

