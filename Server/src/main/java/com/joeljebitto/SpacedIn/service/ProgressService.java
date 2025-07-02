package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.dto.FlashcardDTO;
import com.joeljebitto.SpacedIn.entity.CardProgress;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.CardProgressRepository;
import com.joeljebitto.SpacedIn.repository.FlashcardRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ProgressService {
  private final CardProgressRepository progressRepository;
  private final UserRepository userRepository;
  private final FlashcardRepository cardRepository;

  public ProgressService(CardProgressRepository progressRepository, UserRepository userRepository,
      FlashcardRepository cardRepository) {
    this.progressRepository = progressRepository;
    this.userRepository = userRepository;
    this.cardRepository = cardRepository;
  }

  public List<CardProgress> getUserProgress(Long userId) {
    User user = userRepository.findById(userId).orElseThrow();
    return progressRepository.findByUser(user);
  }

  public CardProgress reviewCard(Long userId, Long cardId, int quality) {
    User user = userRepository.findById(userId).orElseThrow();
    Flashcard card = cardRepository.findById(cardId).orElseThrow();

    CardProgress progress = progressRepository.findByUserAndCard(user, card);
    if (progress == null) {
      progress = new CardProgress();
      progress.setUser(user);
      progress.setCard(card);
      progress.setEasinessFactor(2.5);
      progress.setRepetitions(0);
      progress.setInterval(1);
      progress.setNextReviewDate(LocalDate.now());
    }

    int repetitions = progress.getRepetitions();
    int interval = progress.getInterval();
    double ef = progress.getEasinessFactor();

    if (quality < 3) {
      repetitions = 0;
      interval = 1;
    } else {
      if (repetitions == 0)
        interval = 1;
      else if (repetitions == 1)
        interval = 6;
      else
        interval = (int) Math.round(interval * ef);

      ef += (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02));
      ef = Math.max(1.3, ef);
      repetitions += 1;
    }

    LocalDate today = LocalDate.now();
    progress.setEasinessFactor(ef);
    progress.setRepetitions(repetitions);
    progress.setInterval(interval);
    progress.setLastReviewed(today);
    progress.setNextReviewDate(today.plusDays(interval));

    return progressRepository.save(progress);
  }

  public List<Flashcard> getDueCards(Long deckId, Long userId) {
    User user = userRepository.findById(userId).orElseThrow();
    LocalDate today = LocalDate.now();

    return cardRepository.findByDeckId(deckId).stream()
        .filter(card -> {
          CardProgress p = progressRepository.findByUserAndCard(user, card);
          return (p == null) || !p.getNextReviewDate().isAfter(today);
        })
        .collect(Collectors.toList());
  }
}
