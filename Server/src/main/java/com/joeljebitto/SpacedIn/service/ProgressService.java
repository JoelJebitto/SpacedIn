package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.entity.CardProgress;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.CardProgressRepository;
import com.joeljebitto.SpacedIn.repository.FlashcardRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProgressService {
    private final CardProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final FlashcardRepository cardRepository;

    public ProgressService(CardProgressRepository progressRepository, UserRepository userRepository, FlashcardRepository cardRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    public List<CardProgress> getUserProgress(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return progressRepository.findByUser(user);
    }

    public CardProgress updateProgress(Long userId, Long cardId, int quality) {
        User user = userRepository.findById(userId).orElseThrow();
        Flashcard card = cardRepository.findById(cardId).orElseThrow();
        CardProgress progress = progressRepository
                .findByUser(user)
                .stream()
                .filter(p -> p.getCard().equals(card))
                .findFirst()
                .orElseGet(() -> {
                    CardProgress p = new CardProgress();
                    p.setUser(user);
                    p.setCard(card);
                    p.setEasinessFactor(2.5);
                    p.setIntervalDays(1);
                    return p;
                });

        double ef = Math.max(1.3, progress.getEasinessFactor() + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02)));
        int interval = progress.getRepetitions() == 0 ? 1 : (progress.getRepetitions() == 1 ? 6 : (int) Math.round(progress.getIntervalDays() * ef));

        progress.setRepetitions(progress.getRepetitions() + 1);
        progress.setIntervalDays(interval);
        progress.setEasinessFactor(ef);
        progress.setLastReview(LocalDateTime.now());
        progress.setNextReview(LocalDateTime.now().plusDays(interval));

        return progressRepository.save(progress);
    }
}
