package com.exasol.challenge.poker.exceptions;

public class InvalidCardRankException extends InvalidCardException {
    private final char rank;

    /**
     * Constructs an <code>InvalidCardRankException</code> with the
     * invalid card rank.
     *
     * @param rank invalid card rank.
     */
    public InvalidCardRankException(char rank) {
        super();
        this.rank = rank;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid Card Rank: '%s'.", rank);
    }
}
