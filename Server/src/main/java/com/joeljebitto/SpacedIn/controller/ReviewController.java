package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.CardProgressDTO;
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
    public ResponseEntity<CardProgressDTO> review(@PathVariable Long cardId, @RequestParam Long userId, @RequestParam int quality) {
        CardProgress progress = progressService.reviewCard(userId, cardId, quality);
        return ResponseEntity.ok(new CardProgressDTO(progress));
    }
}
