package com.joeljebitto.SpacedIn.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeljebitto.SpacedIn.DTO.CardProgressRequest;
import com.joeljebitto.SpacedIn.DTO.CardProgressResponse;
import com.joeljebitto.SpacedIn.Entity.Card;
import com.joeljebitto.SpacedIn.Entity.CardProgress;
import com.joeljebitto.SpacedIn.Entity.User;
import com.joeljebitto.SpacedIn.Repository.CardProgressRepository;
import com.joeljebitto.SpacedIn.Repository.CardRepository;
import com.joeljebitto.SpacedIn.Repository.UserRepository;
import com.joeljebitto.SpacedIn.Service.CardProgressService;
import com.joeljebitto.SpacedIn.exception.ResourceNotFoundException;

@Service
@Transactional
public class CardProgressServiceImpl implements CardProgressService {

  private final CardProgressRepository progressRepo;
  private final CardRepository cardRepo;
  private final UserRepository userRepo;

  public CardProgressServiceImpl(CardProgressRepository progressRepo,
      CardRepository cardRepo,
      UserRepository userRepo) {
    this.progressRepo = progressRepo;
    this.cardRepo = cardRepo;
    this.userRepo = userRepo;
  }

  @Override
  public CardProgressResponse createProgress(Long cardId, CardProgressRequest request, Long userId) {
    Card card = cardRepo.findById(cardId)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "id", cardId));
    if (!card.getDeck().getUser().getId().equals(userId)) {
      throw new ResourceNotFoundException("Card", "id", cardId);
    }
    CardProgress progress = progressRepo.findByUserIdAndCardId(userId, cardId);
    if (progress == null) {
      User user = userRepo.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
      progress = new CardProgress(card, user, request.getProgress());
    } else {
      progress.setProgress(request.getProgress());
    }
    CardProgress saved = progressRepo.save(progress);
    return toResponse(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public CardProgressResponse getProgress(Long cardId, Long userId) {
    CardProgress progress = progressRepo.findByUserIdAndCardId(userId, cardId);
    if (progress == null) {
      throw new ResourceNotFoundException("CardProgress", "cardId", cardId);
    }
    return toResponse(progress);
  }

  @Override
  public CardProgressResponse updateProgress(Long cardId, CardProgressRequest request, Long userId) {
    CardProgress progress = progressRepo.findByUserIdAndCardId(userId, cardId);
    if (progress == null) {
      throw new ResourceNotFoundException("CardProgress", "cardId", cardId);
    }
    progress.setProgress(request.getProgress());
    CardProgress saved = progressRepo.save(progress);
    return toResponse(saved);
  }

  @Override
  public void deleteProgress(Long cardId, Long userId) {
    CardProgress progress = progressRepo.findByUserIdAndCardId(userId, cardId);
    if (progress != null) {
      progressRepo.delete(progress);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<CardProgressResponse> getProgressForUser(Long userId) {
    return progressRepo.findByUserId(userId).stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  private CardProgressResponse toResponse(CardProgress progress) {
    return new CardProgressResponse(
        progress.getId(),
        progress.getProgress(),
        progress.getCard().getId(),
        progress.getUser().getId());
  }
}
