package com.joeljebitto.SpacedIn.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeljebitto.SpacedIn.DTO.SimpleCardRequest;
import com.joeljebitto.SpacedIn.DTO.SimpleCardResponse;
import com.joeljebitto.SpacedIn.Entity.Deck;
import com.joeljebitto.SpacedIn.Entity.SimpleCard;
import com.joeljebitto.SpacedIn.Repository.DeckRepository;
import com.joeljebitto.SpacedIn.Repository.SimpleCardRepository;
import com.joeljebitto.SpacedIn.Service.SimpleCardService;
import com.joeljebitto.SpacedIn.exception.ResourceNotFoundException;

@Service
@Transactional
public class SimpleCardServiceImpl implements SimpleCardService {

  private final DeckRepository deckRepo;
  private final SimpleCardRepository cardRepo;

  public SimpleCardServiceImpl(DeckRepository deckRepo, SimpleCardRepository cardRepo) {
    this.deckRepo = deckRepo;
    this.cardRepo = cardRepo;
  }

  @Override
  public SimpleCardResponse createCard(Long deckId, SimpleCardRequest request, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(deckId, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", deckId));

    SimpleCard card = new SimpleCard();
    card.setDeck(deck);
    card.setType("SimpleCard");
    card.setFront(request.getFront());
    card.setBack(request.getBack());

    SimpleCard saved = cardRepo.save(card);
    return toResponse(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public List<SimpleCardResponse> getCardsForDeck(Long deckId, Long userId) {
    deckRepo.findByIdAndUserId(deckId, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", deckId));
    return cardRepo.findByDeckId(deckId).stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public SimpleCardResponse getCardById(Long deckId, Long cardId, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(deckId, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", deckId));
    SimpleCard card = (SimpleCard) cardRepo.findById(cardId)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "id", cardId));
    if (!card.getDeck().getId().equals(deck.getId())) {
      throw new ResourceNotFoundException("Card", "id", cardId);
    }
    return toResponse(card);
  }

  @Override
  public SimpleCardResponse updateCard(Long deckId, Long cardId, SimpleCardRequest request, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(deckId, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", deckId));
    SimpleCard card = (SimpleCard) cardRepo.findById(cardId)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "id", cardId));
    if (!card.getDeck().getId().equals(deck.getId())) {
      throw new ResourceNotFoundException("Card", "id", cardId);
    }
    card.setFront(request.getFront());
    card.setBack(request.getBack());
    SimpleCard updated = cardRepo.save(card);
    return toResponse(updated);
  }

  @Override
  public void deleteCard(Long deckId, Long cardId, Long userId) {
    Deck deck = deckRepo.findByIdAndUserId(deckId, userId)
        .orElseThrow(() -> new ResourceNotFoundException("Deck", "id", deckId));
    SimpleCard card = (SimpleCard) cardRepo.findById(cardId)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "id", cardId));
    if (!card.getDeck().getId().equals(deck.getId())) {
      throw new ResourceNotFoundException("Card", "id", cardId);
    }
    cardRepo.delete(card);
  }

  private SimpleCardResponse toResponse(SimpleCard card) {
    return new SimpleCardResponse(
        card.getId(),
        card.getFront(),
        card.getBack(),
        card.getDeck().getId());
  }
}
