package com.joeljebitto.SpacedIn.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deck_progress", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "deck_id" }))
public class DeckProgress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "deck_id", nullable = false)
  private Deck deck;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false, precision = 5, scale = 2)
  private BigDecimal progress;

  // Constructors
  public DeckProgress() {
  }

  public DeckProgress(Deck deck, User user, BigDecimal progress) {
    this.deck = deck;
    this.user = user;
    this.progress = progress;
  }

  // Getters & Setters
  public Long getId() {
    return id;
  }

  public Deck getDeck() {
    return deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BigDecimal getProgress() {
    return progress;
  }

  public void setProgress(BigDecimal progress) {
    this.progress = progress;
  }
}
