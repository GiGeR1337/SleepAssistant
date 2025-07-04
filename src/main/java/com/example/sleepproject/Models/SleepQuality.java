package com.example.sleepproject.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "sleep_qualities")
public class SleepQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuality;
    private String quality;

    public SleepQuality() {
    }

    public SleepQuality(String quality) {
        this.quality = quality;
    }

    public Long getIdQuality() {
        return idQuality;
    }

    public void setIdQuality(Long idQuality) {
        this.idQuality = idQuality;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
