package com.joeljebitto.SpacedIn.Service;

import java.util.List;

import com.joeljebitto.SpacedIn.DTO.LearningLogRequest;
import com.joeljebitto.SpacedIn.DTO.LearningLogResponse;

public interface LearningLogService {
  LearningLogResponse createLog(Long cardId, LearningLogRequest request, Long userId);

  List<LearningLogResponse> getLogsForCard(Long cardId, Long userId);

  LearningLogResponse getLogById(Long id, Long userId);

  LearningLogResponse updateLog(Long id, LearningLogRequest request, Long userId);

  void deleteLog(Long id, Long userId);
}
