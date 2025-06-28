package com.example.sleepproject.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "sleep_qualities")
public class SleepQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuality;
    private String quality;
}
