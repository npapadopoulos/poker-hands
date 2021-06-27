package com.exasol.challenge.poker.cards;

import com.exasol.challenge.poker.exceptions.InvalidCardException;

public class Card implements Comparable<Card> {

    private final CardRank rank;
    private final CardSuit suit;

    /**
     * Constructs a {@link Card}.
     *
     * @param card {@link String} containing {@link CardRank#getSymbol()} and {@link CardSuit#getSymbol()}.
     */
    public Card(String card) {
        if (card.length() != 2) {
            throw new InvalidCardException(card);
        }
        this.rank = CardRank.parse(card.charAt(0));
        this.suit = CardSuit.parse(card.charAt(1));
    }

    /**
     * Returns the {@link CardRank} of this {@link Card}.
     */
    public CardRank getRank() {
        return this.rank;
    }

    /**
     * Returns the {@link CardSuit} of this {@link Card}.
     */
    public CardSuit getSuit() {
        return this.suit;
    }

    /**
     * Compares this {@link Card} with the specified {@link Card}.
     *
     * @param card specified {@link Card}.
     * @return int a negative integer, zero, or a positive integer as this {@link Card} is less than, equal to, or greater than the specified {@link Card}.
     */
    @Override
    public int compareTo(Card card) {
        return Integer.compare(this.rank.ordinal(), card.rank.ordinal());
    }

    @Override
    public String toString() {
        return this.getRank() + " of " + this.getSuit();
    }
}
