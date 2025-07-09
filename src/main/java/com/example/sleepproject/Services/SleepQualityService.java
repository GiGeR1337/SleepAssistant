package com.example.sleepproject.Services;

import com.example.sleepproject.Models.SleepQuality;
import com.example.sleepproject.Repositories.SleepQualityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SleepQualityService {
    private final SleepQualityRepository sleepQualityRepository;

    public SleepQualityService(SleepQualityRepository sleepQualityRepository) {
        this.sleepQualityRepository = sleepQualityRepository;
    }

    public Optional<SleepQuality> findByIdSleep(Long id) {
        return sleepQualityRepository.findByIdQuality(id);
    }

    public List<SleepQuality> findAll() {
        return sleepQualityRepository.findAll();
    }

    @PostConstruct
    public void init() {
        sleepQualityRepository.save(new SleepQuality("Poor"));
        sleepQualityRepository.save(new SleepQuality("Bad"));
        sleepQualityRepository.save(new SleepQuality("Good"));
        sleepQualityRepository.save(new SleepQuality("Excellent"));
    }
}
