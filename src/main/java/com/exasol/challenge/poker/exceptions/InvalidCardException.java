package com.exasol.challenge.poker.exceptions;

public class InvalidCardException extends IllegalArgumentException {
    /**
     * Constructs an <code>InvalidCardException</code> with no
     * detail message.
     */
    public InvalidCardException() {
        super();
    }

    /**
     * Constructs an <code>InvalidCardException</code> with the
     * invalid card.
     *
     * @param card invalid card.
     */
    public InvalidCardException(String card) {
        super(String.format("Invalid Card: '%s'.", card));
    }
}
