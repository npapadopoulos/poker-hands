package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;
import com.exasol.challenge.poker.hands.HighCardHand;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.Comparator.comparing;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link HighCardHand}.
 */
public class HighCardEvaluator extends Evaluator {

    /**
     * Evaluates the provided array of {@link Card} against {@link HighCardHand} and stores the result in the hand ranks cache, to be used by other {@link Evaluator}s.
     * <p>
     * Note, that the {@link HighCardEvaluator} is the last in order {@link Evaluator}
     * to be called in the chain.
     */
    @Override
    public Optional<Hand> evaluate(Card[] cards) {
        return Arrays.stream(cards)
                .max(comparing(Card::getRank))
                .map(v -> new HighCardHand(getHandRank(), new TreeMap<>(Map.of(v.getRank(), List.of(v.getSuit()))), cards));
    }

    @Override
    public HandRank getHandRank() {
        return HandRank.HIGH_CARD;
    }
}
