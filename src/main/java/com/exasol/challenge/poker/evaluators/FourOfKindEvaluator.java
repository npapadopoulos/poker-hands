package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.hands.HandRank;
import com.exasol.challenge.poker.hands.KindHand;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link KindHand} of {@link HandRank#FOUR_OF_KIND}.
 */
public class FourOfKindEvaluator extends AbstractKindEvaluator {
    @Override
    public HandRank getHandRank() {
        return HandRank.FOUR_OF_KIND;
    }
}
