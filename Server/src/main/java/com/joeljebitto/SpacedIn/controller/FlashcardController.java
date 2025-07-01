package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.FlashcardDTO;
import com.joeljebitto.SpacedIn.dto.FlashcardRequest;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import com.joeljebitto.SpacedIn.service.FlashcardService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlashcardController {
  private final FlashcardService flashcardService;

  public FlashcardController(FlashcardService flashcardService) {
    this.flashcardService = flashcardService;
  }

  @PostMapping("/cards")
  public ResponseEntity<Flashcard> create(@RequestBody FlashcardRequest req) {
    return ResponseEntity.ok(flashcardService.createCard(req));
  }

  @PutMapping("/cards/{id}")
  public ResponseEntity<Flashcard> update(@PathVariable Long id, @RequestBody FlashcardRequest req) {
    return ResponseEntity.ok(flashcardService.updateCard(id, req));
  }

  @DeleteMapping("/cards/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    flashcardService.deleteCard(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/decks/{deckId}/cards")
  public ResponseEntity<List<FlashcardDTO>> getDeckCards(@PathVariable Long deckId) {
    return ResponseEntity.ok(flashcardService.getDeckCards(deckId));
  }
}
