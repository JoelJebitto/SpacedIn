package com.joeljebitto.SpacedIn.Service;

import java.util.List;

import com.joeljebitto.SpacedIn.DTO.DeckRequest;
import com.joeljebitto.SpacedIn.DTO.DeckResponse;

public interface DeckService {
  DeckResponse createDeck(DeckRequest request, Long userId);

  List<DeckResponse> getDecksForUser(Long userId);

  DeckResponse getDeckById(Long id, Long userId);

  DeckResponse updateDeck(Long id, DeckRequest request, Long userId);

  void deleteDeck(Long id, Long userId);
}
