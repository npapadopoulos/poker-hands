package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;

import static com.exasol.challenge.poker.hands.HandRank.BASE_VALUE;

public class StraightHand extends Hand {
    public StraightHand(HandRank handRank, Card[] cards) {
        super(handRank, cards);
    }

    /**
     * Returns the {@link HandRank#STRAIGHT}} {@link StraightHand}'s value by
     * multiplying the ordinal value of the {@link HandRank) by the {@link HandRank#BASE_VALUE} and
     * adding the value of all {@link Card}s {@link CardRank }s.
     */
    @Override
    public double getValue() {
        return BASE_VALUE * getHandRank().ordinal() + super.getValue();
    }

    @Override
    public Card getHighestCard() {
        Card highestCard = super.getHighestCard();
        if (cards[0].getRank() == CardRank.TWO && highestCard.getRank() == CardRank.ACE) {
            return cards[cards.length - 2];
        }
        return highestCard;
    }

    /**
     * Constructs and returns the printable result of template RANK + CARD_RANK + CARD_SUIT.
     * eg. Straight, Seven of Diamonds
     */
    @Override
    public String toString() {
        return getHandRank() + ", " + getHighestCard();
    }
}
