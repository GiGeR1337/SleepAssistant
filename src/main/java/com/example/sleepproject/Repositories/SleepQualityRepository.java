package com.example.sleepproject.Repositories;

import com.example.sleepproject.Models.SleepQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SleepQualityRepository extends JpaRepository<SleepQuality, Long> {
    Optional<SleepQuality> findByIdQuality(Long id);
}
