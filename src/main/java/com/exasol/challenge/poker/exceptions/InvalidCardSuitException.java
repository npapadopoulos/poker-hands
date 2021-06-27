package com.exasol.challenge.poker.exceptions;

public class InvalidCardSuitException extends InvalidCardException {
    private final char suit;

    /**
     * Constructs an <code>InvalidCardSuitException</code> with the
     * invalid card suit.
     *
     * @param suit invalid card suit.
     */
    public InvalidCardSuitException(char suit) {
        super();
        this.suit = suit;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid Card Suit: '%s'.", suit);
    }
}
