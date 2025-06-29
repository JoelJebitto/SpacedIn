package com.joeljebitto.SpacedIn.Service;

import java.util.List;

import com.joeljebitto.SpacedIn.DTO.DeckProgressRequest;
import com.joeljebitto.SpacedIn.DTO.DeckProgressResponse;

public interface DeckProgressService {
  DeckProgressResponse createProgress(Long deckId, DeckProgressRequest request, Long userId);

  DeckProgressResponse getProgress(Long deckId, Long userId);

  DeckProgressResponse updateProgress(Long deckId, DeckProgressRequest request, Long userId);

  void deleteProgress(Long deckId, Long userId);

  List<DeckProgressResponse> getProgressForUser(Long userId);
}
