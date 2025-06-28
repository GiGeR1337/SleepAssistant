package com.example.sleepproject.Repositories;

import com.example.sleepproject.Models.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {
}
