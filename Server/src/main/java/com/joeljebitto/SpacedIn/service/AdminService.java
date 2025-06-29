package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.repository.DeckRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final DeckRepository deckRepository;

    public AdminService(UserRepository userRepository, DeckRepository deckRepository) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
    }

    public void deleteDeck(Long deckId) {
        deckRepository.deleteById(deckId);
    }
}
