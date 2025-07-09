package com.example.sleepproject.Repositories;

import com.example.sleepproject.Models.Sleep;
import com.example.sleepproject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
    List<Sleep> findTop5ByUserOrderByDateDesc(User user);

    List<Sleep> findAllByUser(User user);

    void deleteSleepByIdSleep(Long id);
}
