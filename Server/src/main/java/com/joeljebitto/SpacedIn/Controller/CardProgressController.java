package com.joeljebitto.SpacedIn.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.joeljebitto.SpacedIn.DTO.CardProgressRequest;
import com.joeljebitto.SpacedIn.DTO.CardProgressResponse;
import com.joeljebitto.SpacedIn.Service.CardProgressService;
import com.joeljebitto.SpacedIn.Service.CustomUserDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cards/{cardId}/progress")
public class CardProgressController {

  private final CardProgressService service;

  public CardProgressController(CardProgressService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<CardProgressResponse> createProgress(
      @PathVariable Long cardId,
      @Valid @RequestBody CardProgressRequest request,
      @AuthenticationPrincipal CustomUserDetails user) {
    CardProgressResponse created = service.createProgress(cardId, request, user.getId());
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<CardProgressResponse> getProgress(
      @PathVariable Long cardId,
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.getProgress(cardId, user.getId()));
  }

  @PutMapping
  public ResponseEntity<CardProgressResponse> updateProgress(
      @PathVariable Long cardId,
      @Valid @RequestBody CardProgressRequest request,
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.updateProgress(cardId, request, user.getId()));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteProgress(
      @PathVariable Long cardId,
      @AuthenticationPrincipal CustomUserDetails user) {
    service.deleteProgress(cardId, user.getId());
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/user")
  public ResponseEntity<List<CardProgressResponse>> getUserProgress(
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.getProgressForUser(user.getId()));
  }
}
