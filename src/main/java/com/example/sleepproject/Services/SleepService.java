package com.example.sleepproject.Services;

import com.example.sleepproject.Repositories.SleepRepository;
import org.springframework.stereotype.Service;

@Service
public class SleepService {
    private final SleepRepository sleepRepository;

    public SleepService(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }
}
