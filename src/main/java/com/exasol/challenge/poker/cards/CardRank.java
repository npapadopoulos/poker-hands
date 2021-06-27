package com.exasol.challenge.poker.cards;

import com.exasol.challenge.poker.exceptions.InvalidCardRankException;

import java.util.Arrays;

public enum CardRank {
    TWO(2, '2', "Two"),
    THREE(3, '3', "Three"),
    FOUR(4, '4', "Four"),
    FIVE(5, '5', "Five"),
    SIX(6, '6', "Six"),
    SEVEN(7, '7', "Seven"),
    EIGHT(8, '8', "Eight"),
    NINE(9, '9', "Nine"),
    TEN(10, 'T', "Ten"),
    JACK(11, 'J', "Jack"),
    QUEEN(12, 'Q', "Queen"),
    KING(13, 'K', "King"),
    ACE(14, 'A', "Ace");

    private final int value;
    private final char symbol;
    private final String name;

    /**
     * Constructs a {@link CardRank}.
     *
     * @param value  int value of {@code value}.
     * @param symbol char symbol of {@code symbol}.
     * @param name   String name of a {@code name}.
     */
    CardRank(int value, char symbol, String name) {
        this.value = value;
        this.symbol = symbol;
        this.name = name;
    }

    /**
     * Parses the specified {@code rank} in order to construct a {@link CardRank}.
     *
     * @param rank specified {@code rank} char symbol.
     * @return {@link CardRank} for the specified {@code rank}.
     * @throws InvalidCardRankException if no {@link CardRank} found for the specified {@code rank}.
     */
    public static CardRank parse(char rank) {
        switch (rank) {
            case '2':
                return TWO;
            case '3':
                return THREE;
            case '4':
                return FOUR;
            case '5':
                return FIVE;
            case '6':
                return SIX;
            case '7':
                return SEVEN;
            case '8':
                return EIGHT;
            case '9':
                return NINE;
            case 'T':
                return TEN;
            case 'J':
                return JACK;
            case 'Q':
                return QUEEN;
            case 'K':
                return KING;
            case 'A':
                return ACE;
            default:
                throw new InvalidCardRankException(rank);
        }
    }

    /**
     * Returns an array of {@link Character} of all symbols of predefined {@link CardRank}s.
     */
    public static Character[] symbols() {
        return Arrays.stream(values()).map(CardRank::getSymbol).toArray(Character[]::new);
    }

    /**
     * Returns this {@link CardRank}'s value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns this {@link CardRank}'s symbol.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Overrides {@link Object#toString()} and returns this {@link CardRank}'s name.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
