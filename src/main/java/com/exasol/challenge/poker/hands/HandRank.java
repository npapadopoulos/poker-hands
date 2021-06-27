package com.exasol.challenge.poker.hands;

public enum HandRank {
    HIGH_CARD("High Card"),
    ONE_PAIR("One Pair"),
    TWO_PAIRS("Two Pairs"),
    THREE_OF_KIND("Three Of a Kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full House"),
    FOUR_OF_KIND("Four Of a Kind"),
    STRAIGHT_FLUSH("Straight Flush"),
    ROYAL_FLUSH("Royal Flush");

    public static final double BASE_VALUE = 1000000;

    private final String label;

    HandRank(String label) {
        this.label = label;
    }

    /**
     * Returns the human readable label of the card.
     */
    @Override
    public String toString() {
        return this.label;
    }
}
