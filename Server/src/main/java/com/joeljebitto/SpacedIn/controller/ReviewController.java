package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.entity.CardProgress;
import com.joeljebitto.SpacedIn.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ProgressService progressService;

    public ReviewController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/cards/{cardId}/review")
    public ResponseEntity<CardProgress> review(@PathVariable Long cardId, @RequestParam Long userId, @RequestParam int quality) {
        return ResponseEntity.ok(progressService.reviewCard(userId, cardId, quality));
    }
}
