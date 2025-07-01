package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.dto.StatsDTO;
import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.CardProgressRepository;
import com.joeljebitto.SpacedIn.repository.DeckRepository;
import com.joeljebitto.SpacedIn.repository.FlashcardRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatsService {
    private final UserRepository userRepository;
    private final DeckRepository deckRepository;
    private final FlashcardRepository flashcardRepository;
    private final CardProgressRepository progressRepository;
    private final ProgressService progressService;

    public StatsService(UserRepository userRepository,
                        DeckRepository deckRepository,
                        FlashcardRepository flashcardRepository,
                        CardProgressRepository progressRepository,
                        ProgressService progressService) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
        this.flashcardRepository = flashcardRepository;
        this.progressRepository = progressRepository;
        this.progressService = progressService;
    }

    @Transactional(readOnly = true)
    public StatsDTO getUserStats(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Deck> decks = deckRepository.findByOwnerId(userId);
        int totalCards = decks.stream()
                .mapToInt(d -> flashcardRepository.findByDeckId(d.getId()).size())
                .sum();
        int reviewed = progressRepository.findByUser(user).size();
        int due = decks.stream()
                .mapToInt(d -> progressService.getDueCards(d.getId(), userId).size())
                .sum();
        return new StatsDTO(totalCards, reviewed, due);
    }

    @Transactional(readOnly = true)
    public StatsDTO getDeckStats(Long deckId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Flashcard> cards = flashcardRepository.findByDeckId(deckId);
        int totalCards = cards.size();
        int reviewed = (int) cards.stream()
                .filter(card -> progressRepository.findByUserAndCard(user, card) != null)
                .count();
        int due = progressService.getDueCards(deckId, userId).size();
        return new StatsDTO(totalCards, reviewed, due);
    }
}
