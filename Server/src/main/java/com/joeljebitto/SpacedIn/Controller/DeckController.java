package com.joeljebitto.SpacedIn.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joeljebitto.SpacedIn.DTO.DeckRequest;
import com.joeljebitto.SpacedIn.DTO.DeckResponse;
import com.joeljebitto.SpacedIn.Service.DeckService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/decks")
public class DeckController {
  private final DeckService deckService;

  public DeckController(DeckService deckService) {
    this.deckService = deckService;
  }

  // CREATE
  @PostMapping
  public ResponseEntity<DeckResponse> addDeck(@Valid @RequestBody DeckRequest req) {
    DeckResponse created = deckService.createDeck(req);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  // READ ALL
  @GetMapping
  public ResponseEntity<List<DeckResponse>> listDecks() {
    return ResponseEntity.ok(deckService.getAllDecks());
  }

  // READ ONE
  @GetMapping("/{id}")
  public ResponseEntity<DeckResponse> getDeck(@PathVariable Long id) {
    return ResponseEntity.ok(deckService.getDeckById(id));
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<DeckResponse> updateDeck(
      @PathVariable Long id,
      @Valid @RequestBody DeckRequest req) {
    return ResponseEntity.ok(deckService.updateDeck(id, req));
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
    deckService.deleteDeck(id);
    return ResponseEntity.noContent().build();
  }
}
