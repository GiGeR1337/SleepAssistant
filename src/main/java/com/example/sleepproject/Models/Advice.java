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

    public Advice() {
    }

    public Advice(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Long getIdAdvice() {
        return idAdvice;
    }

    public void setIdAdvice(Long idAdvice) {
        this.idAdvice = idAdvice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

