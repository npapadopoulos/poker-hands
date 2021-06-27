package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;

import static com.exasol.challenge.poker.hands.HandRank.BASE_VALUE;

public class RoyalFlushHand extends Hand {

    public RoyalFlushHand(HandRank handRank, Card[] cards) {
        super(handRank, cards);
    }

    /**
     * Returns the {@link HandRank#ROYAL_FLUSH}} {@link RoyalFlushHand}'s value by
     * multiplying the ordinal value of the {@link HandRank) by the {@link HandRank#BASE_VALUE}.
     */
    @Override
    public double getValue() {
        return BASE_VALUE * getHandRank().ordinal();
    }

    /**
     * Constructs and returns the printable result of template RANK + CARD_SUIT.
     * eg. Royal Flush, Diamonds
     */
    @Override
    public String toString() {
        return getHandRank() + ", " + getHighestCard().getSuit();
    }
}
