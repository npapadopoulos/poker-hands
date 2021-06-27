package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.cards.CardSuit;

import java.util.List;
import java.util.SortedMap;

public class HighCardHand extends Hand {
    public HighCardHand(HandRank handRank, SortedMap<CardRank, List<CardSuit>> importantCards, Card[] cards) {
        super(handRank, importantCards, cards);
    }

    @Override
    public double getValue() {
        return super.getValue();
    }
}
