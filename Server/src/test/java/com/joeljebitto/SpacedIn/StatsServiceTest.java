package com.joeljebitto.SpacedIn;

import com.joeljebitto.SpacedIn.dto.StatsDTO;
import com.joeljebitto.SpacedIn.entity.Deck;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.CardProgressRepository;
import com.joeljebitto.SpacedIn.repository.DeckRepository;
import com.joeljebitto.SpacedIn.repository.FlashcardRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import com.joeljebitto.SpacedIn.service.ProgressService;
import com.joeljebitto.SpacedIn.service.StatsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StatsServiceTest {
    @Test
    void getDeckStatsCountsReviewedAndDue() {
        User user = new User();
        user.setId(1L);
        Deck deck = new Deck();
        deck.setId(5L);
        Flashcard card1 = new Flashcard();
        card1.setId(2L);
        Flashcard card2 = new Flashcard();
        card2.setId(3L);

        UserRepository userRepo = Mockito.mock(UserRepository.class);
        DeckRepository deckRepo = Mockito.mock(DeckRepository.class);
        FlashcardRepository cardRepo = Mockito.mock(FlashcardRepository.class);
        CardProgressRepository progressRepo = Mockito.mock(CardProgressRepository.class);
        ProgressService progressService = Mockito.mock(ProgressService.class);

        Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(cardRepo.findByDeckId(5L)).thenReturn(List.of(card1, card2));
        Mockito.when(progressRepo.findByUserAndCard(user, card1)).thenReturn(null);
        Mockito.when(progressRepo.findByUserAndCard(user, card2)).thenReturn(null);
        Mockito.when(progressService.getDueCards(5L, 1L)).thenReturn(Collections.singletonList(card1));

        StatsService service = new StatsService(userRepo, deckRepo, cardRepo, progressRepo, progressService);
        StatsDTO dto = service.getDeckStats(5L, 1L);
        assertEquals(2, dto.getTotalCards());
        assertEquals(0, dto.getReviewedCards());
        assertEquals(1, dto.getDueCards());
    }
}
