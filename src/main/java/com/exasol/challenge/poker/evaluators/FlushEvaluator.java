package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardSuit;
import com.exasol.challenge.poker.hands.FlushHand;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;

import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link FlushHand}.
 */
public class FlushEvaluator extends Evaluator {

    /**
     * Checks if the provided array of {@link Card} was already evaluated and present in the cache, against {@link FlushHand}.
     * <p>
     * If true, returns the already evaluated {@link FlushHand}, otherwise evaluates the provided array of {@link Card} against the provided {@code rank}.
     * <p>
     * The process of evaluation ends if there are no exactly 5 available same {@link CardSuit} cards and returns {@link Optional#empty()},
     * otherwise constructs and returns the {@link FlushHand}.
     */
    static Optional<Hand> get(Card[] cards, HandRank rank) {
        var processedHand = ofNullable(Evaluator.getHandRanks().get(rank));
        if (processedHand.isPresent()) {
            return processedHand;
        }

        //all cards must have the same suit
        if (stream(cards).anyMatch(card -> card.getSuit() != cards[0].getSuit())) {
            return empty();
        }
        var hand = new FlushHand(rank, cards);
        Evaluator.getHandRanks().put(rank, hand);
        return of(hand);
    }

    /**
     * Returns the evaluated {@link FlushHand}, if present, otherwise calls the next {@link Evaluator} to process the array of {@link Card}.
     */
    @Override
    public Optional<Hand> evaluate(Card[] cards) {
        return get(cards, getHandRank()).or(() -> evaluateNext(cards));
    }

    @Override
    public HandRank getHandRank() {
        return HandRank.FLUSH;
    }
}
