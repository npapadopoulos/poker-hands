package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;

import static com.exasol.challenge.poker.hands.HandRank.BASE_VALUE;

public class FlushHand extends Hand {
    public FlushHand(HandRank handRank, Card[] cards) {
        super(handRank, cards);
    }

    /**
     * Returns the {@link HandRank#FLUSH}} {@link FlushHand}'s value by
     * multiplying the ordinal value of the {@link HandRank) by the {@link HandRank#BASE_VALUE} and
     * adding the value of all {@link Card}s {@link CardRank }s.
     */
    @Override
    public double getValue() {
        return BASE_VALUE * getHandRank().ordinal() + super.getValue();
    }

    /**
     * Constructs and returns the printable result of template RANK + CARD_SUIT.
     * eg. Flush, Diamonds
     */
    @Override
    public String toString() {
        return getHandRank() + ", " + getHighestCard().getSuit();
    }
}
