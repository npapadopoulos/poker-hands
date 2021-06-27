package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;

import static com.exasol.challenge.poker.hands.HandRank.BASE_VALUE;
import static com.exasol.challenge.poker.hands.HandRank.HIGH_CARD;

public class StraightFlushHand extends Hand {
    public StraightFlushHand(HandRank handRank, Card[] cards) {
        super(handRank, cards);
    }

    /**
     * Returns the {@link HandRank#STRAIGHT_FLUSH}} {@link StraightFlushHand}'s value by
     * multiplying the ordinal value of the {@link HandRank) by the {@link HandRank#BASE_VALUE} and
     * adding the value of all {@link Card}s {@link CardRank }s.
     */
    @Override
    public double getValue() {
        return BASE_VALUE * getHandRank().ordinal() + super.getValue();
    }

    /**
     * Constructs and returns the printable result of template RANK + CARD_SUIT + HIGH_CARD.
     * eg. Straight Flush, Diamonds and High Card, King
     */
    @Override
    public String toString() {
        return getHandRank() + ", " + getHighestCard().getSuit() + " and " + HIGH_CARD + ", " + getHighestCard().getRank();
    }
}
