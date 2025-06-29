package com.joeljebitto.SpacedIn.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CardProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Flashcard card;

    private int repetitions;
    private int intervalDays;
    private double easinessFactor;
    private LocalDateTime nextReview;
    private LocalDateTime lastReview;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Flashcard getCard() { return card; }
    public void setCard(Flashcard card) { this.card = card; }
    public int getRepetitions() { return repetitions; }
    public void setRepetitions(int repetitions) { this.repetitions = repetitions; }
    public int getIntervalDays() { return intervalDays; }
    public void setIntervalDays(int intervalDays) { this.intervalDays = intervalDays; }
    public double getEasinessFactor() { return easinessFactor; }
    public void setEasinessFactor(double easinessFactor) { this.easinessFactor = easinessFactor; }
    public LocalDateTime getNextReview() { return nextReview; }
    public void setNextReview(LocalDateTime nextReview) { this.nextReview = nextReview; }
    public LocalDateTime getLastReview() { return lastReview; }
    public void setLastReview(LocalDateTime lastReview) { this.lastReview = lastReview; }
}
