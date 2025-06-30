package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.FlashcardDTO;
import com.joeljebitto.SpacedIn.entity.CardProgress;
import com.joeljebitto.SpacedIn.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ProgressService progressService;

    public ReviewController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/decks/{deckId}/due-cards")
    public ResponseEntity<List<FlashcardDTO>> getDueCards(@PathVariable Long deckId, @RequestParam Long userId) {
        return ResponseEntity.ok(progressService.getDueCards(deckId, userId).stream().map(FlashcardDTO::new).collect(Collectors.toList()));
    }

    @PostMapping("/cards/{cardId}/review")
    public ResponseEntity<CardProgress> review(@PathVariable Long cardId, @RequestParam Long userId, @RequestParam int quality) {
        return ResponseEntity.ok(progressService.reviewCard(userId, cardId, quality));
    }
}
