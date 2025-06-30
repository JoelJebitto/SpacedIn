package com.joeljebitto.SpacedIn.dto;

public class StatsDTO {
    private int totalCards;
    private int reviewedCards;
    private int dueCards;

    public StatsDTO(int totalCards, int reviewedCards, int dueCards) {
        this.totalCards = totalCards;
        this.reviewedCards = reviewedCards;
        this.dueCards = dueCards;
    }

    public int getTotalCards() { return totalCards; }
    public int getReviewedCards() { return reviewedCards; }
    public int getDueCards() { return dueCards; }
}
