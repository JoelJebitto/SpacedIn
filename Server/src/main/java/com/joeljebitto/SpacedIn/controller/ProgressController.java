package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.entity.CardProgress;
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
    public ResponseEntity<List<CardProgress>> getProgress(@PathVariable Long userId) {
        return ResponseEntity.ok(progressService.getUserProgress(userId));
    }

    @PostMapping
    public ResponseEntity<CardProgress> update(@RequestParam Long userId, @RequestParam Long cardId, @RequestParam int quality) {
        return ResponseEntity.ok(progressService.reviewCard(userId, cardId, quality));
    }
}
