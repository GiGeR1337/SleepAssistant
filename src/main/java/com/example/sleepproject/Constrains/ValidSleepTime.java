package com.example.sleepproject.Constrains;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SleepTimeValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSleepTime {
    String message() default "Wake time must be after bedtime and not exceed 20 hours";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}