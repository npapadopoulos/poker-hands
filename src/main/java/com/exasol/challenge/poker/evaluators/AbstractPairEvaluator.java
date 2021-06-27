package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardSuit;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;
import com.exasol.challenge.poker.hands.PairHand;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link PairHand}
 * of {@link HandRank#ONE_PAIR} or {@link HandRank#TWO_PAIRS}.
 *
 * @see OnePairEvaluator
 * @see TwoPairsEvaluator
 */
public abstract class AbstractPairEvaluator extends Evaluator {

    /**
     * Checks if the provided array of {@link Card} was already evaluated and present in the cache, against {@link PairHand}.
     * <p>
     * If true, returns the already evaluated {@link PairHand}, otherwise evaluates the provided array of {@link Card} against the provided {@code rank}, {@link HandRank#ONE_PAIR} or {@link HandRank#TWO_PAIRS}.
     * <p>
     * The process of evaluation ends if there are no pairs of {@link Card} of any {@link CardSuit} available and returns {@link Optional#empty()},
     * otherwise constructs and returns the {@link PairHand} with the calculated important cards.
     */
    static Optional<Hand> get(Card[] cards, HandRank rank) {
        var processedHand = ofNullable(Evaluator.getHandRanks().get(rank));
        if (processedHand.isPresent()) {
            return processedHand;
        }
        var pairs = getCardsRanks(cards).entrySet()
                .stream()
                .filter(c -> c.getValue().size() == 2)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (pairs.size() == rank.ordinal()) {
            var hand = new PairHand(rank, new TreeMap<>(pairs), cards);
            Evaluator.getHandRanks().put(rank, hand);
            return of(hand);
        }
        return empty();
    }

    /**
     * Returns the evaluated {@link PairHand}, if present, otherwise calls the next {@link Evaluator} to process the array of {@link Card}.
     */
    @Override
    public Optional<Hand> evaluate(Card[] cards) {
        return get(cards, getHandRank()).or(() -> evaluateNext(cards));
    }
}
