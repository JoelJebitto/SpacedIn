package com.joeljebitto.SpacedIn.Service;

import java.util.List;

import com.joeljebitto.SpacedIn.DTO.SimpleCardRequest;
import com.joeljebitto.SpacedIn.DTO.SimpleCardResponse;

public interface SimpleCardService {
  SimpleCardResponse createCard(Long deckId, SimpleCardRequest request, Long userId);

  List<SimpleCardResponse> getCardsForDeck(Long deckId, Long userId);

  SimpleCardResponse getCardById(Long deckId, Long cardId, Long userId);

  SimpleCardResponse updateCard(Long deckId, Long cardId, SimpleCardRequest request, Long userId);

  void deleteCard(Long deckId, Long cardId, Long userId);
}
