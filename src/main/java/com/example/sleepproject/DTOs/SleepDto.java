package com.example.sleepproject.DTOs;

import com.example.sleepproject.Constrains.ValidSleepTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalTime;

@ValidSleepTime
public class SleepDto {
    @NotNull(message = "Date must not be null")
    @PastOrPresent(message = "Date must be in the past or present")
    private LocalDate date;
    @NotNull(message = "BedTime must not be null")
    private LocalTime bedtime;
    @NotNull(message = "WakeTime must not be null")
    private LocalTime wakeTime;

    @NotNull(message = "Caffeine before bed must be specified")
    private boolean caffeineBeforeBed;
    @NotNull(message = "Screen before bed must be specified")
    private boolean screenBeforeBed;
    @NotNull(message = "Quality must be specified")
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
