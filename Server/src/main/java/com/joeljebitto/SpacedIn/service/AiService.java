package com.joeljebitto.SpacedIn.service;

import org.springframework.stereotype.Service;

@Service
public class AiService {
    public String explain(String question) {
        // Placeholder for OpenAI API integration
        return "AI explanation for: " + question;
    }
    public String helpCard(Long cardId) {
        return "AI help for card " + cardId;
    }

    public String generateCards(String topic) {
        return "Generated cards for " + topic;
    }
}
