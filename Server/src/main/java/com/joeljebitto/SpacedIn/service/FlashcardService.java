package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.dto.FlashcardRequest;
import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import com.joeljebitto.SpacedIn.repository.DeckRepository;
import com.joeljebitto.SpacedIn.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final DeckRepository deckRepository;

    public FlashcardService(FlashcardRepository flashcardRepository, DeckRepository deckRepository) {
        this.flashcardRepository = flashcardRepository;
        this.deckRepository = deckRepository;
    }

    public Flashcard createCard(FlashcardRequest request) {
        Deck deck = deckRepository.findById(request.getDeckId()).orElseThrow();
        Flashcard card = new Flashcard();
        card.setDeck(deck);
        card.setQuestion(request.getQuestion());
        card.setAnswer(request.getAnswer());
        return flashcardRepository.save(card);
    }

    public Flashcard updateCard(Long id, FlashcardRequest request) {
        Flashcard card = flashcardRepository.findById(id).orElseThrow();
        card.setQuestion(request.getQuestion());
        card.setAnswer(request.getAnswer());
        if (request.getDeckId() != null && !request.getDeckId().equals(card.getDeck().getId())) {
            Deck deck = deckRepository.findById(request.getDeckId()).orElseThrow();
            card.setDeck(deck);
        }
        return flashcardRepository.save(card);
    }

    public void deleteCard(Long id) {
        flashcardRepository.deleteById(id);
    }

    public List<Flashcard> getCards(Long deckId) {
        Deck deck = deckRepository.findById(deckId).orElseThrow();
        return flashcardRepository.findByDeck(deck);
    }
}
