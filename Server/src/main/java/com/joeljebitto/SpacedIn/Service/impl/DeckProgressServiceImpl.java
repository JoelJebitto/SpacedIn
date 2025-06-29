package com.joeljebitto.SpacedIn.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeljebitto.SpacedIn.DTO.DeckProgressRequest;
import com.joeljebitto.SpacedIn.DTO.DeckProgressResponse;
import com.joeljebitto.SpacedIn.Entity.Deck;
import com.joeljebitto.SpacedIn.Entity.DeckProgress;
import com.joeljebitto.SpacedIn.Entity.User;
import com.joeljebitto.SpacedIn.Repository.DeckProgressRepository;
import com.joeljebitto.SpacedIn.Repository.DeckRepository;
import com.joeljebitto.SpacedIn.Repository.UserRepository;
import com.joeljebitto.SpacedIn.Service.DeckProgressService;
import com.joeljebitto.SpacedIn.exception.ResourceNotFoundException;

@Service
@Transactional
public class DeckProgressServiceImpl implements DeckProgressService {

  private final DeckProgressRepository progressRepo;
  private final DeckRepository deckRepo;
  private final UserRepository userRepo;

  public DeckProgressServiceImpl(DeckProgressRepository progressRepo,
      DeckRepository deckRepo,
      UserRepository userRepo) {
    this.progressRepo = progressRepo;
    this.deckRepo = deckRepo;
    this.userRepo = userRepo;
  }

  @Override
  public DeckProgressResponse createProgress(Long deckId, DeckProgressRequest request, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(deckId, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", deckId));
    DeckProgress progress = progressRepo.findByUserIdAndDeckId(userId, deckId);
    if (progress == null) {
      User user = userRepo.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
      progress = new DeckProgress(deck, user, request.getProgress());
    } else {
      progress.setProgress(request.getProgress());
    }
    DeckProgress saved = progressRepo.save(progress);
    return toResponse(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public DeckProgressResponse getProgress(Long deckId, Long userId) {
    DeckProgress progress = progressRepo.findByUserIdAndDeckId(userId, deckId);
    if (progress == null) {
      System.out.print("hiii \n \n\n ");
      throw new ResourceNotFoundException("DeckProgress", "deckId", deckId);
    }
    return toResponse(progress);
  }

  @Override
  public DeckProgressResponse updateProgress(Long deckId, DeckProgressRequest request, Long userId) {
    DeckProgress progress = progressRepo.findByUserIdAndDeckId(userId, deckId);
    if (progress == null) {
      throw new ResourceNotFoundException("DeckProgress", "deckId", deckId);
    }
    progress.setProgress(request.getProgress());
    DeckProgress saved = progressRepo.save(progress);
    return toResponse(saved);
  }

  @Override
  public void deleteProgress(Long deckId, Long userId) {
    DeckProgress progress = progressRepo.findByUserIdAndDeckId(userId, deckId);
    if (progress != null) {
      progressRepo.delete(progress);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<DeckProgressResponse> getProgressForUser(Long userId) {
    return progressRepo.findByUserId(userId).stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  private DeckProgressResponse toResponse(DeckProgress progress) {
    return new DeckProgressResponse(
        progress.getId(),
        progress.getProgress(),
        progress.getDeck().getId(),
        progress.getUser().getId());
  }
}
