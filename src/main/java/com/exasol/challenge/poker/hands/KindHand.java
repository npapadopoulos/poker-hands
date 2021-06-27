package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.cards.CardSuit;

import java.util.List;
import java.util.SortedMap;

import static com.exasol.challenge.poker.hands.HandRank.BASE_VALUE;

public class KindHand extends Hand {
    public KindHand(HandRank handRank, SortedMap<CardRank, List<CardSuit>> importantCards, Card[] cards) {
        super(handRank, importantCards, cards);
    }

    /**
     * Returns the {@link HandRank#THREE_OF_KIND} or {@link HandRank#FOUR_OF_KIND} {@link KindHand}'s value by
     * multiplying the ordinal value of the {@link HandRank) by the {@link HandRank#BASE_VALUE} and
     * adding the highest {@link Card}'s {@link CardRank} from the calcluated important cards.
     */
    @Override
    public double getValue() {
        return BASE_VALUE * getHandRank().ordinal() + getImportantCards().keySet().iterator().next().getValue();
    }
}
