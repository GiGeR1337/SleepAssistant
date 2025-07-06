package com.example.sleepproject.Constrains;

import com.example.sleepproject.DTOs.SleepDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;
import java.time.LocalTime;


public class SleepTimeValidator implements ConstraintValidator<ValidSleepTime, SleepDto> {
    @Override
    public boolean isValid(SleepDto sleepDto, ConstraintValidatorContext context) {
        LocalTime bedtime = sleepDto.getBedtime();
        LocalTime wakeTime = sleepDto.getWakeTime();

        if (bedtime == null || wakeTime == null) {
            return true;
        }

        long sleepMinutes;
        if (wakeTime.isAfter(bedtime)) {
            sleepMinutes = Duration.between(bedtime, wakeTime).toMinutes();
        } else {
            sleepMinutes = Duration.between(bedtime, LocalTime.MIDNIGHT).toMinutes()
                    + Duration.between(LocalTime.MIN, wakeTime).toMinutes();
        }

        return sleepMinutes > 0 && sleepMinutes < 20 * 60;
    }
}
