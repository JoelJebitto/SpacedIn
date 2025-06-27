package com.joeljebitto.SpacedIn.Service;

import java.util.List;

import com.joeljebitto.SpacedIn.DTO.DeckRequest;
import com.joeljebitto.SpacedIn.DTO.DeckResponse;

public interface DeckService {
  DeckResponse createDeck(DeckRequest request);

  DeckResponse getDeckById(Long id);

  List<DeckResponse> getAllDecks();

  DeckResponse updateDeck(Long id, DeckRequest request);

  void deleteDeck(Long id);
}
