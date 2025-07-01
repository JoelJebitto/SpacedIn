package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/ai")
public class AiController {
    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/explain")
    public ResponseEntity<String> explain(@RequestBody String q) {
        return ResponseEntity.ok(aiService.explain(q));
    }


    @GetMapping("/help-card/{id}")
    public ResponseEntity<String> helpCard(@PathVariable Long id) {
        return ResponseEntity.ok(aiService.helpCard(id));
    }

    @PostMapping("/generate-cards")
    public ResponseEntity<String> generate(@RequestBody String topic) {
        return ResponseEntity.ok(aiService.generateCards(topic));
    }

    @PostMapping("/answer")
    public ResponseEntity<String> answer(@RequestBody String question) {
        return ResponseEntity.ok(aiService.generateAnswer(question));
    }

    @GetMapping(value = "/answer-stream", produces = "text/event-stream")
    public SseEmitter answerStream(@RequestParam String question) {
        return aiService.streamAnswer(question);
    }
}
