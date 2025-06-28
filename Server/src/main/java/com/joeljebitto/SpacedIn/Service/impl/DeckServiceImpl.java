package com.joeljebitto.SpacedIn.Service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeljebitto.SpacedIn.DTO.DeckRequest;
import com.joeljebitto.SpacedIn.DTO.DeckResponse;
import com.joeljebitto.SpacedIn.Entity.Deck;
import com.joeljebitto.SpacedIn.Entity.User;
import com.joeljebitto.SpacedIn.Repository.DeckRepository;
import com.joeljebitto.SpacedIn.Repository.UserRepository;
import com.joeljebitto.SpacedIn.Service.DeckService;
import com.joeljebitto.SpacedIn.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeckServiceImpl implements DeckService {
  private final DeckRepository deckRepo;
  private final UserRepository userRepo;

  public DeckServiceImpl(DeckRepository deckRepo, UserRepository userRepo) {
    this.deckRepo = deckRepo;
    this.userRepo = userRepo;
  }

  @Override
  public DeckResponse createDeck(DeckRequest req, Long userId) {
    User user = userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    Deck deck = new Deck();
    deck.setTitle(req.getTitle());
    deck.setDescription(req.getDescription());
    deck.setCreatedOn(LocalDateTime.now());
    deck.setUser(user);

    Deck saved = deckRepo.save(deck);
    return toResponse(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public List<DeckResponse> getDecksForUser(Long userId) {
    return deckRepo.findAllByUserId(userId).stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public DeckResponse getDeckById(Long id, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(id, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", id));
    return toResponse(deck);
  }

  @Override
  public DeckResponse updateDeck(Long id, DeckRequest req, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(id, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", id));
    deck.setTitle(req.getTitle());
    deck.setDescription(req.getDescription());
    Deck updated = deckRepo.save(deck);
    return toResponse(updated);
  }

  @Override
  public void deleteDeck(Long id, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(id, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", id));
    deckRepo.delete(deck);
  }

  private DeckResponse toResponse(Deck deck) {
    return new DeckResponse(
        deck.getId(),
        deck.getTitle(),
        deck.getDescription(),
        deck.getCreatedOn(),
        deck.getUser().getId());
  }
}
