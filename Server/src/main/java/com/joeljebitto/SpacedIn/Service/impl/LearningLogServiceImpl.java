package com.joeljebitto.SpacedIn.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeljebitto.SpacedIn.DTO.LearningLogRequest;
import com.joeljebitto.SpacedIn.DTO.LearningLogResponse;
import com.joeljebitto.SpacedIn.Entity.Card;
import com.joeljebitto.SpacedIn.Entity.LearningLog;
import com.joeljebitto.SpacedIn.Entity.User;
import com.joeljebitto.SpacedIn.Repository.CardRepository;
import com.joeljebitto.SpacedIn.Repository.LearningLogRepository;
import com.joeljebitto.SpacedIn.Repository.UserRepository;
import com.joeljebitto.SpacedIn.Service.LearningLogService;
import com.joeljebitto.SpacedIn.exception.ResourceNotFoundException;

@Service
@Transactional
public class LearningLogServiceImpl implements LearningLogService {

  private final LearningLogRepository logRepo;
  private final CardRepository cardRepo;
  private final UserRepository userRepo;

  public LearningLogServiceImpl(LearningLogRepository logRepo,
      CardRepository cardRepo,
      UserRepository userRepo) {
    this.logRepo = logRepo;
    this.cardRepo = cardRepo;
    this.userRepo = userRepo;
  }

  @Override
  public LearningLogResponse createLog(Long cardId, LearningLogRequest request, Long userId) {
    Card card = cardRepo.findById(cardId)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "id", cardId));
    if (!card.getDeck().getUser().getId().equals(userId)) {
      throw new ResourceNotFoundException("Card", "id", cardId);
    }
    User user = userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    LearningLog log = new LearningLog(card, user, request.getStatus());
    LearningLog saved = logRepo.save(log);
    return toResponse(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public List<LearningLogResponse> getLogsForCard(Long cardId, Long userId) {
    Card card = cardRepo.findById(cardId)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "id", cardId));
    if (!card.getDeck().getUser().getId().equals(userId)) {
      throw new ResourceNotFoundException("Card", "id", cardId);
    }
    return logRepo.findByCardId(cardId).stream()
        .filter(l -> l.getUser().getId().equals(userId))
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public LearningLogResponse getLogById(Long id, Long userId) {
    LearningLog log = logRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("LearningLog", "id", id));
    if (!log.getUser().getId().equals(userId)) {
      throw new ResourceNotFoundException("LearningLog", "id", id);
    }
    return toResponse(log);
  }

  @Override
  public LearningLogResponse updateLog(Long id, LearningLogRequest request, Long userId) {
    LearningLog log = logRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("LearningLog", "id", id));
    if (!log.getUser().getId().equals(userId)) {
      throw new ResourceNotFoundException("LearningLog", "id", id);
    }
    log.setStatus(request.getStatus());
    LearningLog saved = logRepo.save(log);
    return toResponse(saved);
  }

  @Override
  public void deleteLog(Long id, Long userId) {
    LearningLog log = logRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("LearningLog", "id", id));
    if (!log.getUser().getId().equals(userId)) {
      throw new ResourceNotFoundException("LearningLog", "id", id);
    }
    logRepo.delete(log);
  }

  private LearningLogResponse toResponse(LearningLog log) {
    return new LearningLogResponse(
        log.getId(),
        log.getStatus(),
        log.getEventTime(),
        log.getCard().getId(),
        log.getUser().getId());
  }
}
