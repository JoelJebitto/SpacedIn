package com.joeljebitto.SpacedIn.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.joeljebitto.SpacedIn.DTO.LearningLogRequest;
import com.joeljebitto.SpacedIn.DTO.LearningLogResponse;
import com.joeljebitto.SpacedIn.Service.CustomUserDetails;
import com.joeljebitto.SpacedIn.Service.LearningLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cards/{cardId}/logs")
public class LearningLogController {

  private final LearningLogService service;

  public LearningLogController(LearningLogService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<LearningLogResponse> createLog(
      @PathVariable Long cardId,
      @Valid @RequestBody LearningLogRequest request,
      @AuthenticationPrincipal CustomUserDetails user) {
    LearningLogResponse created = service.createLog(cardId, request, user.getId());
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<LearningLogResponse>> listLogs(
      @PathVariable Long cardId,
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.getLogsForCard(cardId, user.getId()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<LearningLogResponse> getLog(
      @PathVariable Long id,
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.getLogById(id, user.getId()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<LearningLogResponse> updateLog(
      @PathVariable Long id,
      @Valid @RequestBody LearningLogRequest request,
      @AuthenticationPrincipal CustomUserDetails user) {
    return ResponseEntity.ok(service.updateLog(id, request, user.getId()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLog(
      @PathVariable Long id,
      @AuthenticationPrincipal CustomUserDetails user) {
    service.deleteLog(id, user.getId());
    return ResponseEntity.noContent().build();
  }
}
