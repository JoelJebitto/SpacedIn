package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.DeckRequest;
import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Deck> createDeck(@PathVariable Long userId, @RequestBody DeckRequest request) {
        return ResponseEntity.ok(deckService.createDeck(userId, request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Deck>> getUserDecks(@PathVariable Long userId) {
        return ResponseEntity.ok(deckService.getUserDecks(userId));
    }
}
