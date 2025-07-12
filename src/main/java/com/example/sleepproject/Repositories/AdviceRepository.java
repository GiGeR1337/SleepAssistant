package com.example.sleepproject.Repositories;

import com.example.sleepproject.Models.Advice;
import com.example.sleepproject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {
    Optional<Advice> findTopByUserOrderByIdAdviceDesc(User user);
}
