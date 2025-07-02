package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.CardProgressDTO;
import com.joeljebitto.SpacedIn.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-progress")
public class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CardProgressDTO>> getProgress(@PathVariable Long userId) {
        return ResponseEntity.ok(
                progressService.getUserProgress(userId)
                        .stream()
                        .map(CardProgressDTO::new)
                        .toList());
    }

    @PostMapping
    public ResponseEntity<CardProgressDTO> update(@RequestParam Long userId, @RequestParam Long cardId, @RequestParam int quality) {
        return ResponseEntity.ok(new CardProgressDTO(progressService.reviewCard(userId, cardId, quality)));
    }
}
