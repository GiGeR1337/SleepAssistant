package com.example.sleepproject.Repositories;

import com.example.sleepproject.Models.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
}
