package com.joeljebitto.SpacedIn.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.joeljebitto.SpacedIn.DTO.DeckProgressRequest;
import com.joeljebitto.SpacedIn.DTO.DeckProgressResponse;
import com.joeljebitto.SpacedIn.Service.CustomUserDetails;
import com.joeljebitto.SpacedIn.Service.DeckProgressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/decks")
public class DeckProgressController {

  private final DeckProgressService service;

  public DeckProgressController(DeckProgressService service) {
    this.service = service;
  }

  @PostMapping("/{deckId}/progress")
  public ResponseEntity<DeckProgressResponse> createProgress(
      @PathVariable Long deckId,
      @Valid @RequestBody DeckProgressRequest request,
      @AuthenticationPrincipal CustomUserDetails user) {
    DeckProgressResponse created = service.createProgress(deckId, request, user.getId());
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @GetMapping("/{deckId}/progress")
  public ResponseEntity<DeckProgressResponse> getProgress(
      @PathVariable Long deckId,
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.getProgress(deckId, user.getId()));
  }

  @PutMapping("/{deckId}/progress")
  public ResponseEntity<DeckProgressResponse> updateProgress(
      @PathVariable Long deckId,
      @Valid @RequestBody DeckProgressRequest request,
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.updateProgress(deckId, request, user.getId()));
  }

  @DeleteMapping("/{deckId}/progress")
  public ResponseEntity<Void> deleteProgress(
      @PathVariable Long deckId,
      @AuthenticationPrincipal CustomUserDetails user) {
    service.deleteProgress(deckId, user.getId());
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/progress/user")
  public ResponseEntity<List<DeckProgressResponse>> getUserProgress(
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.getProgressForUser(user.getId()));
  }
}
