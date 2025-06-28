package com.example.sleepproject.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "advices")
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdvice;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

