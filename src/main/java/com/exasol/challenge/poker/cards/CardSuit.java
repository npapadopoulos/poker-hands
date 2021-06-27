package com.exasol.challenge.poker.cards;

import com.exasol.challenge.poker.exceptions.InvalidCardSuitException;

import java.util.Arrays;

public enum CardSuit {
    SPADES('S', "Spades"),
    HEARTS('H', "Hearts"),
    DIAMONDS('D', "Diamonds"),
    CLUBS('C', "Clubs");

    private final char symbol;
    private final String label;

    /**
     * Constructs a {@link CardSuit}.
     *
     * @param symbol char symbol of {@code symbol}.
     * @param label  String label of a {@code name}.
     */
    CardSuit(char symbol, String label) {
        this.symbol = symbol;
        this.label = label;
    }

    /**
     * Parses the specified {@code suit} in order to construct a {@link CardSuit}.
     *
     * @param suit specified {@code suit} char symbol.
     * @return {@link CardSuit} for the specified {@code suit}.
     * @throws InvalidCardSuitException if no {@link CardSuit} found for the specified {@code suit}.
     */
    public static CardSuit parse(char suit) {
        switch (suit) {
            case 'H':
                return HEARTS;
            case 'C':
                return CLUBS;
            case 'S':
                return SPADES;
            case 'D':
                return DIAMONDS;
            default:
                throw new InvalidCardSuitException(suit);
        }
    }

    /**
     * Returns an array of {@link Character} of all symbols of predefined {@link CardSuit}s.
     */
    public static Character[] symbols() {
        return Arrays.stream(values()).map(CardSuit::getSymbol).toArray(Character[]::new);
    }

    /**
     * Returns this {@link CardSuit}'s symbol.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Overrides {@link Object#toString()} and returns this {@link CardSuit}'s label.
     */
    @Override
    public String toString() {
        return this.label;
    }
}
