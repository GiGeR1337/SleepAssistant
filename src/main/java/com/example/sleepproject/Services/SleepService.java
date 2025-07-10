package com.example.sleepproject.Services;

import com.example.sleepproject.DTOs.SleepDto;
import com.example.sleepproject.Models.Sleep;
import com.example.sleepproject.Models.SleepQuality;
import com.example.sleepproject.Models.User;
import com.example.sleepproject.Repositories.SleepQualityRepository;
import com.example.sleepproject.Repositories.SleepRepository;
import com.example.sleepproject.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepService {
    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;
    private final SleepQualityRepository sleepQualityRepository;

    public SleepService(SleepRepository sleepRepository, UserRepository userRepository, SleepQualityRepository sleepQualityRepository) {
        this.sleepRepository = sleepRepository;
        this.userRepository = userRepository;
        this.sleepQualityRepository = sleepQualityRepository;
    }

    public List<Sleep> getLast5SleepsForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return sleepRepository.findTop5ByUserOrderByBedtimeDesc(user);
    }

    public List<Sleep> getAllSleeps() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return sleepRepository.findAllByUser(user);
    }

    public void addSleep(SleepDto sleepDto) {
        Sleep sleep = new Sleep();

        applyDtoToSleep(sleepDto, sleep);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        sleep.setUser(user);

        sleepRepository.save(sleep);
    }

    @Transactional
    public void deleteSleepById(Long id) {
        sleepRepository.deleteSleepByIdSleep(id);
    }

    public void editSleep(Long id, SleepDto editedSleepDto) {
        Sleep sleep = sleepRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sleep ID"));

        applyDtoToSleep(editedSleepDto, sleep);

        sleepRepository.save(sleep);
    }

    private void applyDtoToSleep(SleepDto sleepDto, Sleep sleep) {
        sleep.setBedtime(sleepDto.getBedtime());
        sleep.setWakeTime(sleepDto.getWakeTime());
        sleep.setCaffeineBeforeBed(sleepDto.isCaffeineBeforeBed());
        sleep.setScreenBeforeBed(sleepDto.isScreenBeforeBed());

        SleepQuality quality = sleepQualityRepository.findById(sleepDto.getIdQuality())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sleep quality"));

        sleep.setSleepQuality(quality);
    }

    public SleepDto getSleepDtoById(Long id) {
        Sleep sleep = sleepRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sleep ID"));

        SleepDto dto = new SleepDto();
        dto.setBedtime(sleep.getBedtime());
        dto.setWakeTime(sleep.getWakeTime());
        dto.setCaffeineBeforeBed(sleep.isCaffeineBeforeBed());
        dto.setScreenBeforeBed(sleep.isScreenBeforeBed());
        dto.setIdQuality(sleep.getSleepQuality().getIdQuality());

        return dto;
    }

}
