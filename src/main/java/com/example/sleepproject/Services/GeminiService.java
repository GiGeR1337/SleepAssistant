package com.example.sleepproject.Services;

import com.example.sleepproject.Configurations.EnvLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String apiKey;

    public GeminiService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta")
                .build();
        this.apiKey = EnvLoader.dotenv.get("GOOGLE_API_KEY");
    }

    public String getAdviceFromGemini(String prompt) {
        Map<String, Object> requestBody = buildRequestBody(prompt);

        try {
            Mono<Map> responseMono = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/models/gemini-2.5-pro:generateContent")
                            .queryParam("key", apiKey)
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class);

            Map response = responseMono.block();

            return extractAdviceFromResponse(response);

        } catch (Exception e) {
            return "Failed to get advice from Gemini: " + e.getMessage();
        }
    }

    private Map<String, Object> buildRequestBody(String prompt) {
        return Map.of(
                "contents", List.of(
                        Map.of(
                                "role", "user",
                                "parts", List.of(Map.of("text", prompt))
                        )
                )
        );
    }


    @SuppressWarnings("unchecked")
    private String extractAdviceFromResponse(Map<String, Object> response) {
        if (response == null || !response.containsKey("candidates")) {
            return "No advice available at the moment.";
        }

        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
        if (candidates == null || candidates.isEmpty()) {
            return "No advice available at the moment.";
        }

        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
        if (content == null || !content.containsKey("parts")) {
            return "No text content found in Gemini response.";
        }

        List<Map<String, String>> parts = (List<Map<String, String>>) content.get("parts");
        if (parts == null || parts.isEmpty() || parts.get(0).get("text") == null) {
            return "No text content found in Gemini response.";
        }

        return parts.get(0).get("text");
    }
}



