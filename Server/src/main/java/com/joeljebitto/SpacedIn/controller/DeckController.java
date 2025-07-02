package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.DeckDTO;
import com.joeljebitto.SpacedIn.dto.DeckRequest;
import com.joeljebitto.SpacedIn.dto.FlashcardDTO;
import com.joeljebitto.SpacedIn.service.DeckService;
import com.joeljebitto.SpacedIn.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
  private final DeckService deckService;
  private final ProgressService progressService;

  public DeckController(DeckService deckService, ProgressService progressService) {
    this.deckService = deckService;
    this.progressService = progressService;
  }

  @PostMapping("/{userId}")
  public ResponseEntity<DeckDTO> createDeck(@PathVariable Long userId, @RequestBody DeckRequest request) {
    return ResponseEntity.ok(new DeckDTO(deckService.createDeck(userId, request)));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<DeckDTO>> getUserDecks(@PathVariable Long userId) {
    return ResponseEntity.ok(deckService.getUserDecks(userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DeckDTO> updateDeck(@PathVariable Long id, @RequestBody DeckRequest request) {
    return ResponseEntity.ok(new DeckDTO(deckService.updateDeck(id, request)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
    deckService.deleteDeck(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{deckId}/due-cards")
  public ResponseEntity<List<FlashcardDTO>> getDueCards(@PathVariable Long deckId, @RequestParam Long userId) {
    return ResponseEntity.ok(progressService.getDueCards(deckId, userId)
        .stream()
        .map(FlashcardDTO::new)
        .collect(Collectors.toList()));
  }

  @GetMapping("/share/{id}")
  public ResponseEntity<DeckDTO> shareDeck(@PathVariable Long id) {
    return ResponseEntity.ok(new DeckDTO(deckService.getDeck(id)));
  }
}
