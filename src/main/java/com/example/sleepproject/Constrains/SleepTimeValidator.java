package com.example.sleepproject.Constrains;

import com.example.sleepproject.DTOs.SleepDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;
import java.time.LocalDateTime;


public class SleepTimeValidator implements ConstraintValidator<ValidSleepTime, SleepDto> {
    @Override
    public boolean isValid(SleepDto sleepDto, ConstraintValidatorContext context) {
        LocalDateTime bedtime = sleepDto.getBedtime();
        LocalDateTime wakeTime = sleepDto.getWakeTime();

        if (bedtime == null || wakeTime == null) return true;

        Duration duration = Duration.between(bedtime, wakeTime);
        long minutes = duration.toMinutes();

        return minutes > 0 && minutes < 20 * 60;
    }
}
