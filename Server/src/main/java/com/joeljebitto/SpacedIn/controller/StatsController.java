package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.StatsDTO;
import com.joeljebitto.SpacedIn.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StatsDTO> userStats(@PathVariable Long userId) {
        return ResponseEntity.ok(statsService.getUserStats(userId));
    }

    @GetMapping("/deck/{deckId}")
    public ResponseEntity<StatsDTO> deckStats(@PathVariable Long deckId, @RequestParam Long userId) {
        return ResponseEntity.ok(statsService.getDeckStats(deckId, userId));
    }
}
