package com.joeljebitto.SpacedIn;

import com.joeljebitto.SpacedIn.entity.CardProgress;
import com.joeljebitto.SpacedIn.entity.Flashcard;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.CardProgressRepository;
import com.joeljebitto.SpacedIn.repository.FlashcardRepository;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import com.joeljebitto.SpacedIn.service.ProgressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProgressServiceTest {
    @Test
    void updateProgressIncreasesRepetitions() {
        User user = new User();
        user.setId(1L);
        Flashcard card = new Flashcard();
        card.setId(2L);

        UserRepository userRepo = Mockito.mock(UserRepository.class);
        FlashcardRepository cardRepo = Mockito.mock(FlashcardRepository.class);
        CardProgressRepository progressRepo = Mockito.mock(CardProgressRepository.class);

        Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(cardRepo.findById(2L)).thenReturn(Optional.of(card));
        Mockito.when(progressRepo.save(Mockito.any())).thenAnswer(i -> i.getArgument(0));

        ProgressService service = new ProgressService(progressRepo, userRepo, cardRepo);
        CardProgress p = service.updateProgress(1L,2L,5);
        assertEquals(1, p.getRepetitions());
    }
}
