package com.joeljebitto.SpacedIn.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CardProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Flashcard card;

    @Column(name = "easiness_factor")
    private double easinessFactor = 2.5;

    private int repetitions = 0;

    @Column(name = "interval_days")
    private int interval;

    @Column(name = "last_review")
    private LocalDate lastReviewed;

    @Column(name = "next_review")
    private LocalDate nextReviewDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Flashcard getCard() { return card; }
    public void setCard(Flashcard card) { this.card = card; }
    public int getRepetitions() { return repetitions; }
    public void setRepetitions(int repetitions) { this.repetitions = repetitions; }
    public int getInterval() { return interval; }
    public void setInterval(int interval) { this.interval = interval; }
    public double getEasinessFactor() { return easinessFactor; }
    public void setEasinessFactor(double easinessFactor) { this.easinessFactor = easinessFactor; }
    public LocalDate getNextReviewDate() { return nextReviewDate; }
    public void setNextReviewDate(LocalDate nextReviewDate) { this.nextReviewDate = nextReviewDate; }
    public LocalDate getLastReviewed() { return lastReviewed; }
    public void setLastReviewed(LocalDate lastReviewed) { this.lastReviewed = lastReviewed; }
}
