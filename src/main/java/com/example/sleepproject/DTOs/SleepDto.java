package com.example.sleepproject.DTOs;

import com.example.sleepproject.Constrains.ValidSleepTime;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ValidSleepTime
public class SleepDto {
    @NotNull(message = "Bedtime must not be null")
    private LocalDateTime bedtime;

    @NotNull(message = "Wake time must not be null")
    private LocalDateTime wakeTime;

    @NotNull(message = "Caffeine before bed must be specified")
    private boolean caffeineBeforeBed;

    @NotNull(message = "Screen before bed must be specified")
    private boolean screenBeforeBed;

    @NotNull(message = "Quality must be specified")
    private Long idQuality;

    public SleepDto() {
    }

    public LocalDateTime getBedtime() {
        return bedtime;
    }

    public void setBedtime(LocalDateTime bedtime) {
        this.bedtime = bedtime;
    }

    public LocalDateTime getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(LocalDateTime wakeTime) {
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
