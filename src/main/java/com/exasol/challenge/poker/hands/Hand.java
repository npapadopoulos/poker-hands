package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.cards.CardSuit;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

public abstract class Hand {

    final Card[] cards;
    private final HandRank handRank;
    private final SortedMap<CardRank, List<CardSuit>> importantCards;
    private String rawCards;
    private int id;

    public Hand(HandRank handRank, Card[] cards) {
        this.handRank = handRank;
        this.importantCards = Collections.emptySortedMap();
        this.cards = cards;
    }

    public Hand(HandRank handRank, SortedMap<CardRank, List<CardSuit>> importantCards, Card[] cards) {
        this.handRank = handRank;
        this.importantCards = importantCards;
        this.cards = cards;
    }

    /**
     * Returns the calculated sorted important cards map.
     */
    public SortedMap<CardRank, List<CardSuit>> getImportantCards() {
        return importantCards;
    }

    /**
     * Calculates the value of all {@link Card}s {@link CardRank }s.
     */
    public double getValue() {
        double value = this.cards[0].getRank().getValue();
        for (var i = 1; i < this.cards.length; i++) {
            value = value + this.cards[i].getRank().getValue() * Math.pow(CardRank.values().length, i);
        }
        return value;
    }

    /**
     * Constructs and returns the printable result of {@link HandRank} and important cards.
     */
    public String toString() {
        return getHandRank() + ", " + getImportantCards().keySet().iterator().next();
    }

    /**
     * Returns the player id.
     */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the {@link HandRank}.
     */
    public HandRank getHandRank() {
        return handRank;
    }

    /**
     * Returns the last card in the sorted array of {@link Card}s as the highest card.
     */
    public Card getHighestCard() {
        return this.cards[this.cards.length - 1];
    }

    /**
     * Returns the initial String representing the hand.
     */
    public String getRawCards() {
        return this.rawCards;
    }

    public void setRawCards(String rawCards) {
        this.rawCards = rawCards;
    }
}