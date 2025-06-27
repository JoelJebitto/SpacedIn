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
  public DeckResponse createDeck(DeckRequest req) {
    User user = userRepo.findById(req.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", req.getUserId()));
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
  public DeckResponse getDeckById(Long id) {
    Deck deck = deckRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", id));
    return toResponse(deck);
  }

  @Override
  @Transactional(readOnly = true)
  public List<DeckResponse> getAllDecks() {
    return deckRepo.findAll().stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public DeckResponse updateDeck(Long id, DeckRequest req) {
    Deck deck = deckRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", id));
    // Optionally update owner:
    if (!deck.getUser().getId().equals(req.getUserId())) {
      User newUser = userRepo.findById(req.getUserId())
          .orElseThrow(() -> new ResourceNotFoundException("User", "id", req.getUserId()));
      deck.setUser(newUser);
    }
    deck.setTitle(req.getTitle());
    deck.setDescription(req.getDescription());
    Deck updated = deckRepo.save(deck);
    return toResponse(updated);
  }

  @Override
  public void deleteDeck(Long id) {
    if (!deckRepo.existsById(id)) {
      throw new ResourceNotFoundException("Deck", "id", id);
    }
    deckRepo.deleteById(id);
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
