package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.dto.DeckRequest;
import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.DeckRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Deck> getUserDecks(Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();
        return deckRepository.findByOwner(owner);
    }
}
