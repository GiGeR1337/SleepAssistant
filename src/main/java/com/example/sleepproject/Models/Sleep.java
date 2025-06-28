package com.example.sleepproject.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sleeps")
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSleep;

    private LocalDate date;

    private LocalTime bedtime;
    private LocalTime wakeTime;

    private boolean caffeineBeforeBed;
    private boolean screenBeforeBed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sleep_quality_id")
    private SleepQuality sleepQuality;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getBedtime() {
        return bedtime;
    }

    public void setBedtime(LocalTime bedtime) {
        this.bedtime = bedtime;
    }

    public LocalTime getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(LocalTime wakeTime) {
        this.wakeTime = wakeTime;
    }

    public boolean isCaffeineBeforeBed() {
        return caffeineBeforeBed;
    }

    public void setCaffeineBeforeBed(boolean caffeineBeforeBed) {
        this.caffeineBeforeBed = caffeineBeforeBed;
    }

    public boolean isScreenBeforeBed() {
        return screenBeforeBed;
    }

    public void setScreenBeforeBed(boolean screenBeforeBed) {
        this.screenBeforeBed = screenBeforeBed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(SleepQuality sleepQuality) {
        this.sleepQuality = sleepQuality;
    }
}

