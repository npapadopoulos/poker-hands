package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;
import com.exasol.challenge.poker.hands.KindHand;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link KindHand}
 * of {@link HandRank#THREE_OF_KIND} or {@link HandRank#FOUR_OF_KIND}.
 *
 * @see ThreeOfKindEvaluator
 * @see FourOfKindEvaluator
 */
public abstract class AbstractKindEvaluator extends Evaluator {

    /**
     * Checks if the provided array of {@link Card} was already evaluated and present in the cache, against {@link KindHand}.
     * <p>
     * If true, returns the already evaluated {@link KindHand}, otherwise evaluates the provided array of {@link Card} against the provided {@code rank}, {@link HandRank#FOUR_OF_KIND} or {@link HandRank#THREE_OF_KIND}.
     * <p>
     * The process of evaluation ends if there are no 3 or 4 available same {@link CardRank} cards and returns {@link Optional#empty()},
     * otherwise constructs and returns the {@link KindHand} with the calculated {@code kinds} as important cards.
     */
    static Optional<Hand> get(Card[] cards, HandRank rank) {
        var processedHand = ofNullable(Evaluator.getHandRanks().get(rank));
        if (processedHand.isPresent()) {
            //already evaluated for the rank
            return processedHand;
        }

        //process the important cards, threes or fours
        var kinds = getCardsRanks(cards).entrySet()
                .stream()
                .filter(e -> e.getValue().size() == Double.valueOf(Math.floor(Integer.valueOf(rank.ordinal()).doubleValue() / 3 + 2)).intValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (kinds.isEmpty()) {
            return empty();
        }
        var hand = new KindHand(rank, new TreeMap<>(kinds), cards);
        //update the hands ranks cache with the hand result
        Evaluator.getHandRanks().put(rank, hand);
        return of(hand);
    }

    /**
     * Returns the evaluated {@link KindHand}, if present, otherwise calls the next {@link Evaluator} to process the array of {@link Card}.
     */
    @Override
    public Optional<Hand> evaluate(Card[] cards) {
        return get(cards, getHandRank()).or(() -> evaluateNext(cards));
    }
}
