package com.joeljebitto.SpacedIn.dto;

import com.joeljebitto.SpacedIn.entity.CardProgress;

import java.time.LocalDate;

public class CardProgressDTO {
    private Long id;
    private Long cardId;
    private Long userId;
    private double easinessFactor;
    private int repetitions;
    private int interval;
    private LocalDate lastReviewed;
    private LocalDate nextReviewDate;

    public CardProgressDTO(CardProgress progress) {
        this.id = progress.getId();
        if (progress.getCard() != null) {
            this.cardId = progress.getCard().getId();
        }
        if (progress.getUser() != null) {
            this.userId = progress.getUser().getId();
        }
        this.easinessFactor = progress.getEasinessFactor();
        this.repetitions = progress.getRepetitions();
        this.interval = progress.getInterval();
        this.lastReviewed = progress.getLastReviewed();
        this.nextReviewDate = progress.getNextReviewDate();
    }

    public Long getId() { return id; }
    public Long getCardId() { return cardId; }
    public Long getUserId() { return userId; }
    public double getEasinessFactor() { return easinessFactor; }
    public int getRepetitions() { return repetitions; }
    public int getInterval() { return interval; }
    public LocalDate getLastReviewed() { return lastReviewed; }
    public LocalDate getNextReviewDate() { return nextReviewDate; }
}
