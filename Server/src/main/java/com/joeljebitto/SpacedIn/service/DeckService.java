package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.dto.DeckDTO;
import com.joeljebitto.SpacedIn.dto.DeckRequest;
import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.DeckRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckService {
  private final DeckRepository deckRepository;
  private final UserRepository userRepository;

  public DeckService(DeckRepository deckRepository, UserRepository userRepository) {
    this.deckRepository = deckRepository;
    this.userRepository = userRepository;
  }

  public Deck createDeck(Long userId, DeckRequest request) {
    User owner = userRepository.findById(userId).orElseThrow();
    Deck deck = new Deck();
    deck.setTitle(request.getTitle());
    deck.setOwner(owner);
    return deckRepository.save(deck);
  }

  public List<DeckDTO> getUserDecks(Long userId) {
    return deckRepository.findByOwnerId(userId)
        .stream()
        .map(DeckDTO::new)
        .collect(Collectors.toList());
  }

  public Deck updateDeck(Long deckId, DeckRequest request) {
    Deck deck = deckRepository.findById(deckId).orElseThrow();
    deck.setTitle(request.getTitle());
    return deckRepository.save(deck);
  }

  public void deleteDeck(Long id) {
    deckRepository.deleteById(id);
  }

  public Deck getDeck(Long id) {
    return deckRepository.findById(id).orElseThrow();
  }
}
