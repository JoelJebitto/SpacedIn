package com.joeljebitto.SpacedIn.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.joeljebitto.SpacedIn.DTO.SimpleCardRequest;
import com.joeljebitto.SpacedIn.DTO.SimpleCardResponse;
import com.joeljebitto.SpacedIn.Service.CustomUserDetails;
import com.joeljebitto.SpacedIn.Service.SimpleCardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/decks/{deckId}/cards")
public class SimpleCardController {

  private final SimpleCardService cardService;

  public SimpleCardController(SimpleCardService cardService) {
    this.cardService = cardService;
  }

  // CREATE
  @PostMapping
  public ResponseEntity<SimpleCardResponse> addCard(
      @PathVariable Long deckId,
      @Valid @RequestBody SimpleCardRequest req,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    SimpleCardResponse created = cardService.createCard(deckId, req, userDetails.getId());
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  // READ ALL FOR DECK
  @GetMapping
  public ResponseEntity<List<SimpleCardResponse>> listCards(
      @PathVariable Long deckId,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return ResponseEntity.ok(cardService.getCardsForDeck(deckId, userDetails.getId()));
  }

  // READ ONE
  @GetMapping("/{id}")
  public ResponseEntity<SimpleCardResponse> getCard(
      @PathVariable Long deckId,
      @PathVariable Long id,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return ResponseEntity.ok(cardService.getCardById(deckId, id, userDetails.getId()));
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<SimpleCardResponse> updateCard(
      @PathVariable Long deckId,
      @PathVariable Long id,
      @Valid @RequestBody SimpleCardRequest req,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return ResponseEntity.ok(cardService.updateCard(deckId, id, req, userDetails.getId()));
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCard(
      @PathVariable Long deckId,
      @PathVariable Long id,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    cardService.deleteCard(deckId, id, userDetails.getId());
    return ResponseEntity.noContent().build();
  }
}
