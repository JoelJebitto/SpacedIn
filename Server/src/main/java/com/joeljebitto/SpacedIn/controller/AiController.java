package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
