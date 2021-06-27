package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.hands.FullHouseHand;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;
import com.exasol.challenge.poker.hands.KindHand;
import com.exasol.challenge.poker.hands.PairHand;

import java.util.Optional;

import static java.util.Optional.of;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link FullHouseHand}.
 */
public class FullHouseEvaluator extends Evaluator {

    /**
     * Checks if the provided array of {@link Card} was already evaluated and present in the cache,
     * against {@link KindHand} of {@link HandRank#THREE_OF_KIND} or {@link HandRank#FOUR_OF_KIND}
     * and {@link PairHand} of {@link HandRank#ONE_PAIR}. If true, constructs and returns
     * the {@link FullHouseHand} with the pre-calculated important cards form {@link ThreeOfKindEvaluator},
     * otherwise calls the next {@link Evaluator} to process the array of {@link Card}.
     */
    @Override
    public Optional<Hand> evaluate(Card[] cards) {
        var threes = AbstractKindEvaluator.get(cards, HandRank.THREE_OF_KIND);
        var pair = AbstractPairEvaluator.get(cards, HandRank.ONE_PAIR);
        if (threes.isPresent() && pair.isPresent()) {
            return of(new FullHouseHand(getHandRank(), threes.get().getImportantCards(), cards));
        }
        return evaluateNext(cards);
    }

    @Override
    public HandRank getHandRank() {
        return HandRank.FULL_HOUSE;
    }
}
