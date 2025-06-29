package com.joeljebitto.SpacedIn.Service;

import java.util.List;

import com.joeljebitto.SpacedIn.DTO.CardProgressRequest;
import com.joeljebitto.SpacedIn.DTO.CardProgressResponse;

public interface CardProgressService {
  CardProgressResponse createProgress(Long cardId, CardProgressRequest request, Long userId);

  CardProgressResponse getProgress(Long cardId, Long userId);

  CardProgressResponse updateProgress(Long cardId, CardProgressRequest request, Long userId);

  void deleteProgress(Long cardId, Long userId);

  List<CardProgressResponse> getProgressForUser(Long userId);
}
