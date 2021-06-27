package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.cards.CardSuit;

import java.util.List;
import java.util.SortedMap;

import static com.exasol.challenge.poker.hands.HandRank.BASE_VALUE;

public class FullHouseHand extends Hand {
    public FullHouseHand(HandRank handRank, SortedMap<CardRank, List<CardSuit>> importantCards, Card[] cards) {
        super(handRank, importantCards, cards);
    }

    @Override
    public double getValue() {
        return BASE_VALUE * getHandRank().ordinal() + getImportantCards().keySet().iterator().next().getValue();
    }
}
