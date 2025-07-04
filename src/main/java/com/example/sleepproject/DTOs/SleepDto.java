package com.example.sleepproject.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class SleepDto {
    private LocalDate date;
    private LocalTime bedtime;
    private LocalTime wakeTime;

    private boolean caffeineBeforeBed;
    private boolean screenBeforeBed;
    private Long idQuality;

    public SleepDto() {
    }

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

    public Long getIdQuality() {
        return idQuality;
    }

    public void setIdQuality(Long idQuality) {
        this.idQuality = idQuality;
    }
}
