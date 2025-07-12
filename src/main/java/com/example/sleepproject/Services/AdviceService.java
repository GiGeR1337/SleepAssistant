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
    private final GeminiService geminiService;

    public AdviceService(AdviceRepository adviceRepository, SleepRepository sleepRepository, UserRepository userRepository, GeminiService geminiService) {
        this.adviceRepository = adviceRepository;
        this.sleepRepository = sleepRepository;
        this.userRepository = userRepository;
        this.geminiService = geminiService;
    }

    private static String getPromptForRecentSleeps(List<Sleep> recentSleeps) {
        StringBuilder sleepSummary = new StringBuilder();
        for (Sleep sleep : recentSleeps) {
            sleepSummary.append(String.format(
                    "Bedtime: %s, Wake: %s, Quality (1-Poor, 2-Bad, 3-Good, 4-Excellent): %s, Caffeine: %s, Screen: %s\n",
                    sleep.getBedtime(), sleep.getWakeTime(),
                    sleep.getSleepQuality().getIdQuality(),
                    sleep.isCaffeineBeforeBed(), sleep.isScreenBeforeBed()
            ));
        }
        return "Give me very short personalized advice for sleep improvement based on this data:\n" + sleepSummary;
    }

    public void getAdviceForCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Sleep> recentSleeps = sleepRepository.findTop5ByUserOrderByBedtimeDesc(user);
        if (recentSleeps.isEmpty()) {
            return;
        }

        String prompt = getPromptForRecentSleeps(recentSleeps);
        String content = geminiService.getAdviceFromGemini(prompt);
        adviceRepository.save(new Advice(content, user));
    }


    public String getLastAdviceForCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Sleep> recentSleeps = sleepRepository.findTop5ByUserOrderByBedtimeDesc(user);
        if (recentSleeps.isEmpty()) {
            return "Please provide sleep information to receive advice.";
        }

        return adviceRepository.findTopByUserOrderByIdAdviceDesc(user)
                .map(Advice::getContent)
                .orElse("No advice available. Click the button to generate one.");
    }
}