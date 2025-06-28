package com.joeljebitto.SpacedIn.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.joeljebitto.SpacedIn.DTO.DeckRequest;
import com.joeljebitto.SpacedIn.DTO.DeckResponse;
import com.joeljebitto.SpacedIn.Service.CustomUserDetails;
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
  public ResponseEntity<DeckResponse> addDeck(
      @Valid @RequestBody DeckRequest req,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    DeckResponse created = deckService.createDeck(req, userDetails.getId());
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  // READ ALL FOR USER
  @GetMapping
  public ResponseEntity<List<DeckResponse>> listDecks(
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return ResponseEntity.ok(deckService.getDecksForUser(userDetails.getId()));
  }

  // READ ONE (must belong to user)
  @GetMapping("/{id}")
  public ResponseEntity<DeckResponse> getDeck(
      @PathVariable Long id,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return ResponseEntity.ok(deckService.getDeckById(id, userDetails.getId()));
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<DeckResponse> updateDeck(
      @PathVariable Long id,
      @Valid @RequestBody DeckRequest req,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return ResponseEntity.ok(
        deckService.updateDeck(id, req, userDetails.getId()));
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDeck(
      @PathVariable Long id,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    deckService.deleteDeck(id, userDetails.getId());
    return ResponseEntity.noContent().build();
  }
}
