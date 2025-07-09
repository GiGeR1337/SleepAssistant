package com.example.sleepproject.Services;

import com.example.sleepproject.Models.Advice;
import com.example.sleepproject.Models.Sleep;
import com.example.sleepproject.Models.User;
import com.example.sleepproject.Repositories.AdviceRepository;
import com.example.sleepproject.Repositories.SleepRepository;
import com.example.sleepproject.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceService {
    private final AdviceRepository adviceRepository;
    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;

    public AdviceService(AdviceRepository adviceRepository, SleepRepository sleepRepository, UserRepository userRepository) {
        this.adviceRepository = adviceRepository;
        this.sleepRepository = sleepRepository;
        this.userRepository = userRepository;
    }

    private static String generateAdvice(double averageSleepQuality) {
        String content;

        if (averageSleepQuality == 0) {
            content = "No sleep records";
        } else if (averageSleepQuality > 0 && averageSleepQuality <= 2) {
            content = "Your sleep quality has been low. Try reducing screen time and caffeine before bed.";
        } else if (averageSleepQuality > 2 && averageSleepQuality <= 3.5) {
            content = "Your sleep is average. Consider a consistent bedtime schedule.";
        } else {
            content = "Great job! Your sleep quality looks good. Keep up your healthy habits!";
        }
        return content;
    }

    public String getAdviceForCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Sleep> recentSleeps = sleepRepository.findTop5ByUserOrderByDateDesc(user);

        double averageSleepQuality = recentSleeps.stream().mapToDouble(s -> s.getSleepQuality().getIdQuality()).average().orElse(0);

        String content = generateAdvice(averageSleepQuality);

        Advice advice = new Advice(content, user);
        adviceRepository.save(advice);

        return content;
    }
}
